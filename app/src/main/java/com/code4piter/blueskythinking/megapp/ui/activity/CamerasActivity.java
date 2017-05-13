package com.code4piter.blueskythinking.megapp.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.RequestCameraListDto;
import com.code4piter.blueskythinking.megapp.request.CameraAPI;
import com.code4piter.blueskythinking.megapp.request.RetrofitAPIClient;
import com.code4piter.blueskythinking.megapp.ui.adapter.CamerasAdapter;
import com.code4piter.blueskythinking.megapp.utils.TrackGPS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CamerasActivity extends AppCompatActivity {
    public static final String TAG = CamerasActivity.class.getSimpleName();
    private TrackGPS mLocation;
    private CamerasAdapter mAdapter;
    @BindView(R.id.searchView) SearchView mSearch;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_activity);
        ButterKnife.bind(this);
        final Retrofit retrofit = RetrofitAPIClient.getClient();
        final CameraAPI api = retrofit.create(CameraAPI.class);
        mLocation = new TrackGPS(this);
        mAdapter = new CamerasAdapter(new ArrayList<CameraDto>());
        mRecyclerView.setAdapter(mAdapter);
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: ");
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CamerasActivity.this);
                int dangerLevel = pref.getInt(FilterActivity.PREF_DANGER_LEVEL,0);
                int distance = pref.getInt(FilterActivity.PREF_DISTANCE,5);
                String sortBy = pref.getString(FilterActivity.PREF_SORT_BY,"danger_level");
                double lat = mLocation.getLatitude();
                double lng = mLocation.getLongitude();
                RequestCameraListDto cameraListDto = new RequestCameraListDto();
                cameraListDto.setLatitude((long) lat);
                cameraListDto.setLongitude((long) lng);
                cameraListDto.setDangerLevel((double) dangerLevel);
                cameraListDto.setSearch(query);
                cameraListDto.setSortBy(sortBy);
                cameraListDto.setDistance(distance);
                Call<List<CameraDto>> call = api.getCamerasList(cameraListDto);
                call.enqueue(new Callback<List<CameraDto>>() {
                    @Override
                    public void onResponse(Call<List<CameraDto>> call, Response<List<CameraDto>> response) {
                        mAdapter.setData(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<CameraDto>> call, Throwable throwable) {

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
