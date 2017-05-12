package com.code4piter.blueskythinking.megapp;

import android.app.Application;

import com.code4piter.blueskythinking.megapp.provider.components.AppComponent;
import com.code4piter.blueskythinking.megapp.provider.components.DaggerAppComponent;
import com.code4piter.blueskythinking.megapp.provider.modules.AppModule;


public class App extends Application {

	private static AppComponent component;

	public static AppComponent getComponent() {
		return component;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		initDaggerComponent();
	}

	private void initDaggerComponent() {
		component = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}
}
