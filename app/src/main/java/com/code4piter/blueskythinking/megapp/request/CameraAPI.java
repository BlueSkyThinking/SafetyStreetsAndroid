package com.code4piter.blueskythinking.megapp.request;

import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CameraAPI {
	@GET("lookup")
	Call<CameraDto> get(@Query("key") String key, @Query("text") String text,
	                    @Query("lang") String lang);
}
