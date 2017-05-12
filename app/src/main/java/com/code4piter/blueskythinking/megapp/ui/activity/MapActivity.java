package com.code4piter.blueskythinking.megapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.Camera;
import com.code4piter.blueskythinking.megapp.ui.adapter.CameraAdapter;
import com.code4piter.blueskythinking.megapp.ui.listeners.RecyclerItemClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

	@BindView(R.id.menuRecyclerView)
	RecyclerView menuRecyclerView;
	CameraAdapter cameraAdapter;
	private GoogleMap mMap;

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
	}

	private void setupNavigationCamerasAdapter() {
		List<Camera> cameras = new ArrayList<>();
		LatLng latLng = new LatLng(59.9352276, 30.3212176);
		cameras.add(new Camera("Невский проспект", "Невский проспект, д.27", latLng,
				"http://google.com"));
		cameras.add(new Camera("Невский проспект", "Невский проспект, д.27", latLng,
				"http://google.com"));

		cameraAdapter = new CameraAdapter(cameras);
		menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		menuRecyclerView.setAdapter(cameraAdapter);
		cameraAdapter.notifyDataSetChanged();

		menuRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(MapActivity.this, PlaceActivity.class);
				startActivity(intent);
			}
		}));
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;

		LatLng piter = new LatLng(59.9352276, 30.3212176);
		mMap.addMarker(new MarkerOptions().position(piter).title("Marker in Saint-Petersburg"));
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(piter, 14.0f));
	}
}
