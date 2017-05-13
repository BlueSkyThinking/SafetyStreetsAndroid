package com.code4piter.blueskythinking.megapp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.RequestCameraListDto;
import com.code4piter.blueskythinking.megapp.request.CameraAPI;
import com.code4piter.blueskythinking.megapp.request.RetrofitAPIClient;
import com.code4piter.blueskythinking.megapp.ui.adapter.CamerasAdapter;
import com.code4piter.blueskythinking.megapp.ui.listeners.RecyclerItemClickListener;
import com.code4piter.blueskythinking.megapp.utils.OnLocationChange;
import com.code4piter.blueskythinking.megapp.utils.TrackGPS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.code4piter.blueskythinking.megapp.ui.activity.PlaceActivity.CAMERA_ID;

public class SearchActivity extends AppCompatActivity {
	public static final String TAG = SearchActivity.class.getSimpleName();
	@BindView(R.id.searchView)
	SearchView mSearch;
	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;
	private TrackGPS mLocation;
	private CamerasAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		ButterKnife.bind(this);

		mLocation = new TrackGPS(this, new OnLocationChange() {
			@Override
			public void doOnLocationChange(Location location) {

			}
		});

		mAdapter = new CamerasAdapter(new ArrayList<CameraDto>());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
		mRecyclerView.setAdapter(mAdapter);

		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				startPlaceActivity(mAdapter.getItem(position).getId());
			}
		}));

		setupSearch();
		setupActionBar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_filter:
				Intent intent = new Intent(SearchActivity.this, FilterActivity.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void startPlaceActivity(Long id) {
		Intent intent = new Intent(SearchActivity.this, PlaceActivity.class);
		intent.putExtra(CAMERA_ID, id);
		startActivity(intent);
	}

	private void setupActionBar() {
		ActionBar toolbar = getSupportActionBar();
		if (toolbar != null) {
			toolbar.setDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupSearch() {

		final Retrofit retrofit = RetrofitAPIClient.getClient();
		final CameraAPI api = retrofit.create(CameraAPI.class);
		mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				Log.d(TAG, "onQueryTextSubmit: ");
				SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SearchActivity.this);
				boolean sortDirection = pref.getBoolean(FilterActivity.PREF_SORT_DIRECTION, true);
				int distance = pref.getInt(FilterActivity.PREF_DISTANCE, 5);
				String sortBy = pref.getString(FilterActivity.PREF_SORT_BY, "danger_level");
				double lat = mLocation.getLatitude();
				double lng = mLocation.getLongitude();
				final RequestCameraListDto cameraListDto = new RequestCameraListDto();
				cameraListDto.setLatitude(lat);
				cameraListDto.setLongitude(lng);
//                cameraListDto.setDangerLevel((double) dangerLevel);
				cameraListDto.setSearch(query);
				cameraListDto.setSortBy(sortBy);
				cameraListDto.setDistance(distance * 1000);
				cameraListDto.setSortDirection(sortDirection);
				Call<List<CameraDto>> call = api.getAllCamerasBySearch(cameraListDto);
				call.enqueue(new Callback<List<CameraDto>>() {
					@Override
					public void onResponse(Call<List<CameraDto>> call, Response<List<CameraDto>> response) {
						Log.d(TAG, "onResponse: " + response.toString());
						if (response.body() != null)
							Log.d(TAG, "onResponse: " + response.body().toString());
						if (!response.isSuccessful()) {
							return;
						}

						mAdapter.setData(response.body());
					}

					@Override
					public void onFailure(Call<List<CameraDto>> call, Throwable throwable) {
						throwable.printStackTrace();
					}
				});
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.d(TAG, "onQueryTextChange: ");
				return false;
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		mLocation.stopUsingGPS();
	}
}
