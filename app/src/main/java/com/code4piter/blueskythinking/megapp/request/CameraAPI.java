package com.code4piter.blueskythinking.megapp.request;

import com.code4piter.blueskythinking.megapp.model.dto.CameraDetailDto;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.LocationDto;
import com.code4piter.blueskythinking.megapp.model.dto.MapCameraDto;
import com.code4piter.blueskythinking.megapp.model.dto.NearCamerasDto;
import com.code4piter.blueskythinking.megapp.model.dto.RequestCameraListDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CameraAPI {
	@POST("/camera/search")
	Call<List<CameraDto>> getAllCamerasBySearch(@Body RequestCameraListDto requestCameraListDto);

	@GET("/camera/get/{cameraId}")
	Call<CameraDetailDto> getDetailCamera(@Path("cameraId") Long id);

	@POST("/camera/getNearCameras")
	Call<List<NearCamerasDto>> getNearCameras(@Body LocationDto location);

	@GET("/camera/getAll")
	Call<List<MapCameraDto>> getAllCamerasForMap();

}
