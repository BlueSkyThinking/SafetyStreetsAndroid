package com.code4piter.blueskythinking.megapp.request;

import android.location.Location;

import com.code4piter.blueskythinking.megapp.model.dto.CameraDetailDto;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.MapCameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.NearCamerasDto;
import com.code4piter.blueskythinking.megapp.model.dto.RequestCameraListDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CameraAPI {
	@GET("/camera/search")
		Call<List<CameraDto>> getAllCameras(@Body RequestCameraListDto requestCameraListDto);

	@GET("/camera/get/{cameraId}")
	Call<CameraDetailDto> getDetailCamera(@Path("cameraId") Long id);

	@GET("/camera/getNearCameras")
	Call<List<NearCamerasDto>> getNearCameras(@Body Location location);

	@GET("/camera/getAll")
	Call<List<MapCameraDto>> getAllCamerasForMap();
}
