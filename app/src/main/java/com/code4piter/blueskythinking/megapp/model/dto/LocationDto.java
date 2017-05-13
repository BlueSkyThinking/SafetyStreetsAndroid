package com.code4piter.blueskythinking.megapp.model.dto;

public class LocationDto {

	private Double latitude;
	private Double longitude;

	public LocationDto() {
	}

	public LocationDto(Double latitude, Double longtitude) {
		this.latitude = latitude;
		this.longitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longitude = longtitude;
	}
}
