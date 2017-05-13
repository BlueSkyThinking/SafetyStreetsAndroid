package com.code4piter.blueskythinking.megapp.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPIClient {
	private static final String BASE_URL = "http://172.16.60.123:8080";

	private static Retrofit retrofit = null;

	private RetrofitAPIClient() {
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
