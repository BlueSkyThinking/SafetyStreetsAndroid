package com.code4piter.blueskythinking.megapp.model;

import com.google.android.gms.maps.model.LatLng;


public class Camera {

	private String name;

	private String address;

	private LatLng geoPoint;

	private String streamLink;

	public Camera() {
	}

	public Camera(String name, String address, LatLng geoPoint, String streamLink) {
		this.name = name;
		this.address = address;
		this.geoPoint = geoPoint;
		this.streamLink = streamLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LatLng getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(LatLng geoPoint) {
		this.geoPoint = geoPoint;
	}

	public String getStreamLink() {
		return streamLink;
	}

	public void setStreamLink(String streamLink) {
		this.streamLink = streamLink;
	}
}
