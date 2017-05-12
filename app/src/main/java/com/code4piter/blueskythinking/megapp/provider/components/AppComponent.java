package com.code4piter.blueskythinking.megapp.provider.components;


import com.code4piter.blueskythinking.megapp.provider.modules.AppModule;
import com.code4piter.blueskythinking.megapp.ui.activity.MapActivity;
import com.code4piter.blueskythinking.megapp.utils.TrackGPS;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Komdosh
 *         created on 15.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
	void inject(MapActivity mapActivity);

	void inject(TrackGPS trackGPS);
}