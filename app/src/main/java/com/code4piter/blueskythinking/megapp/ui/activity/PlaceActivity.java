package com.code4piter.blueskythinking.megapp.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDetailDto;
import com.code4piter.blueskythinking.megapp.request.CameraAPI;
import com.code4piter.blueskythinking.megapp.request.RetrofitAPIClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceActivity extends AppCompatActivity {

	public static final String TAG = PlaceActivity.class.getSimpleName();

	public static final String CAMERA_ID = "cameraId";

	@BindView(R.id.preview)
	ImageView preview;
	@BindView(R.id.name)
	TextView name;
	@BindView(R.id.address)
	TextView address;
	@BindView(R.id.dangerLevel)
	TextView dangerLevel;

	private Long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place);
		ButterKnife.bind(this);

		if (getIntent() != null) {
			id = getIntent().getLongExtra(CAMERA_ID, -1);
		}

		requestCameraDetailInfo();
	}

	private void requestCameraDetailInfo() {
		CameraAPI cameraAPI = RetrofitAPIClient.getClient().create(CameraAPI.class);
		cameraAPI.getDetailCamera(id).enqueue(new Callback<CameraDetailDto>() {
			@Override
			public void onResponse(Call<CameraDetailDto> call, Response<CameraDetailDto> response) {

				if (!response.isSuccessful()) {
					return;
				}

				CameraDetailDto cameraDetailDto = response.body();

				if (cameraDetailDto.getImageBase64() != null) {
					byte[] decodedString = Base64.decode(cameraDetailDto.getImageBase64(), Base64.DEFAULT);
					Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
					preview.setImageBitmap(decodedByte);
				}
				name.setText(cameraDetailDto.getName());
				address.setText(cameraDetailDto.getAddress());
				if (cameraDetailDto.getDangerLevel() != null) {
					dangerLevel.setText(cameraDetailDto.getDangerLevel().toString());
				}
			}

			@Override
			public void onFailure(Call<CameraDetailDto> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
