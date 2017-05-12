package com.code4piter.blueskythinking.megapp.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CameraAPIClient {
	private static final String BASE_URL = "https://localhost";

	private static Retrofit retrofit = null;

	private CameraAPIClient() {
	}

	public static Retrofit getClient() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
