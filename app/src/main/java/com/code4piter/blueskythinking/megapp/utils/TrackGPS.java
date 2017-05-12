package com.code4piter.blueskythinking.megapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.permissions.PermissionHelper;

public class TrackGPS extends Service implements LocationListener {

	private static final String TAG = TrackGPS.class.getSimpleName();
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 500;
	private static final long MIN_TIME_BW_UPDATES = (1000 * 60); //in millis
	protected LocationManager locationManager;
	Context context;
	boolean checkGPS = false;
	boolean checkNetwork = false;
	boolean canGetLocation = false;
	Location loc;
	double latitude;
	double longitude;

	public TrackGPS(Context context) {
		this.context = context;
		getLoc();
	}

	private Location getLocation() {
		try {
			locationManager = (LocationManager) context
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			checkGPS = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			checkNetwork = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!checkGPS && !checkNetwork) {
				Log.d(TAG, "GPS and Network currently unavailable");
			} else {
				this.canGetLocation = true;
				if (checkGPS) {
					if (loc == null) {
						try {
							locationManager.requestLocationUpdates(
									LocationManager.GPS_PROVIDER,
									MIN_TIME_BW_UPDATES,
									MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
							if (locationManager != null) {
								loc = locationManager
										.getLastKnownLocation(LocationManager.GPS_PROVIDER);
								if (loc != null) {
									latitude = loc.getLatitude();
									longitude = loc.getLongitude();
								}
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}

				if (checkNetwork) {
					try {
						locationManager.requestLocationUpdates(
								LocationManager.NETWORK_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("Network", "Network");
						if (locationManager != null) {
							loc = locationManager
									.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

						}

						if (loc != null) {
							latitude = loc.getLatitude();
							longitude = loc.getLongitude();
						}
					} catch (SecurityException e) {

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loc;
	}

	public double getLongitude() {
		if (loc != null) {
			longitude = loc.getLongitude();
		}
		return longitude;
	}

	public double getLatitude() {
		if (loc != null) {
			latitude = loc.getLatitude();
		}
		return latitude;
	}

	public Location getLoc() {
		loc = null;
		return getLocation();
	}

	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		alertDialog.setTitle(context.getString(R.string.gpsIsNotEnabledTitle));

		alertDialog.setMessage(context.getString(R.string.gpsIsNotEnabledText));

		alertDialog.setPositiveButton(context.getString(R.string.Yes), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(intent);
			}
		});


		alertDialog.setNegativeButton(context.getString(R.string.No), new DialogInterface
				.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		PermissionHelper.verifyLocationPermission((Activity) context);

		alertDialog.show();
	}


	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(TrackGPS.this);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onStatusChanged(String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled(String s) {

	}

	@Override
	public void onProviderDisabled(String s) {

	}
}
