package com.code4piter.blueskythinking.megapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.MapCameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.NearCamerasDto;
import com.code4piter.blueskythinking.megapp.permissions.PermissionHelper;
import com.code4piter.blueskythinking.megapp.request.CameraAPI;
import com.code4piter.blueskythinking.megapp.request.RetrofitAPIClient;
import com.code4piter.blueskythinking.megapp.ui.adapter.CameraAdapter;
import com.code4piter.blueskythinking.megapp.ui.listeners.RecyclerItemClickListener;
import com.code4piter.blueskythinking.megapp.utils.TrackGPS;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.code4piter.blueskythinking.megapp.permissions.PermissionHelper.REQUEST_LOCATION;
import static com.code4piter.blueskythinking.megapp.ui.activity.PlaceActivity.CAMERA_ID;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

	private static final String TAG = MapActivity.class.getSimpleName();

	@BindView(R.id.menuRecyclerView)
	RecyclerView menuRecyclerView;

	private CameraAdapter cameraAdapter;

	private GoogleMap map;

	private TrackGPS location;

	private List<NearCamerasDto> nearCamerasDtos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		ButterKnife.bind(this);

		setupNavigationCamerasAdapter();
		setupAllMapCameras();
	}

	private void setupNavigationCamerasAdapter() {
		nearCamerasDtos = new ArrayList<>();

		cameraAdapter = new CameraAdapter(nearCamerasDtos);
		menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		menuRecyclerView.setAdapter(cameraAdapter);
		cameraAdapter.notifyDataSetChanged();

		menuRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				startPlaceActivity(cameraAdapter.getItem(position).getId());
			}
		}));
	}

	private void startPlaceActivity(Long id) {
		Intent intent = new Intent(MapActivity.this, PlaceActivity.class);
		intent.putExtra(CAMERA_ID, id);
		startActivity(intent);
	}


	private void setupAllMapCameras() {
		CameraAPI cameraAPI = RetrofitAPIClient.getClient().create(CameraAPI.class);
		cameraAPI.getAllCamerasForMap().enqueue(new Callback<List<MapCameraDto>>() {
			@Override
			public void onResponse(Call<List<MapCameraDto>> call, Response<List<MapCameraDto>> response) {
				if (!response.isSuccessful()) {
					return;
				}
				List<MapCameraDto> mapCameraDtos = response.body();
				for (MapCameraDto mapCameraDto : mapCameraDtos) {
					map.addMarker(new MarkerOptions().position(new LatLng(mapCameraDto.getLatitude(),
							mapCameraDto.getLongitude())).title(mapCameraDto.getId().toString()));
				}
				map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
					@Override
					public void onInfoWindowClick(Marker marker) {
						startPlaceActivity(Long.parseLong(marker.getTitle()));
					}
				});
			}

			@Override
			public void onFailure(Call<List<MapCameraDto>> call, Throwable t) {
				Toast.makeText(MapActivity.this, getApplicationContext().getString(R.string
						.failedToLoadAllMapPoints), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		map = googleMap;

		map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(59.9352276, 30.3212176), 14.0f));
		setupPersonLocation();
	}

	private void setupPersonLocation() {
		if (!PermissionHelper.verifyLocationPermission(this)) {

			if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
					== PackageManager.PERMISSION_GRANTED) {
				map.setMyLocationEnabled(true);
			}

			location = new TrackGPS(this, nearCamerasDtos, cameraAdapter);
			if (!location.canGetLocation()) {
				location.showSettingsAlert();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String permissions[], int[] grantResults) {
		switch (requestCode) {
			case REQUEST_LOCATION:
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					setupPersonLocation();
				}
		}
	}
}
