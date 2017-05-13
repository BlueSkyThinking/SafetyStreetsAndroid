package com.code4piter.blueskythinking.megapp.model.dto;

public class CameraDto {

	private Long id;

	private String name;

	private String address;

	private Long latitude;

	private Long longitude;

	private Double dangerLevel;

	private String imageBase64;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Double getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(Double dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
}
