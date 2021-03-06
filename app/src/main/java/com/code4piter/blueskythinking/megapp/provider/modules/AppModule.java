package com.code4piter.blueskythinking.megapp.provider.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Komdosh
 *         created on 15.04.2017.
 */
@Module
public class AppModule {
	private Context appContext;

	public AppModule(@NonNull Context context) {
		appContext = context;
	}

	@Provides
	@Singleton
	Context provideContext() {
		return appContext;
	}

}
