package com.code4piter.blueskythinking.megapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.request.CameraAPI;
import com.code4piter.blueskythinking.megapp.request.RetrofitAPIClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceActivity extends AppCompatActivity {

	public static final String CAMERA_ID = "cameraId";

	@BindView(R.id.preview)
	ImageView preview;
	@BindView(R.id.name)
	TextView name;
	@BindView(R.id.address)
	TextView address;
	@BindView(R.id.dangerLevel)
	TextView dangerLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place);
		ButterKnife.bind(this);
	}

	private void requestCameraDetailInfo() {
		CameraAPI cameraAPI = RetrofitAPIClient.getClient().create(CameraAPI.class);

	}
}
