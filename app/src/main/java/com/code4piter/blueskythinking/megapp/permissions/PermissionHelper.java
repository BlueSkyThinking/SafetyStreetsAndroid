package com.code4piter.blueskythinking.megapp.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionHelper {

	public static final int REQUEST_LOCATION = 1;

	private static String[] PERMISSIONS_LOCATION = {
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.ACCESS_FINE_LOCATION
	};

	public static boolean verifyLocationPermission(Activity activity) {
		// Check if we have read or write permission
		int locationFine = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
		int locationCoarse = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);

		if (locationFine != PackageManager.PERMISSION_GRANTED || locationCoarse != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(
					activity,
					PERMISSIONS_LOCATION,
					REQUEST_LOCATION
			);
		}

		return locationFine != PackageManager.PERMISSION_GRANTED || locationCoarse != PackageManager
				.PERMISSION_GRANTED;
	}

}
