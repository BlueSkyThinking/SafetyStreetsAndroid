package com.code4piter.blueskythinking.megapp.model.dto;

public class CameraDto {

	private Long id;

	private String name;

	private String address;

	private Double latitude;

	private Double longitude;

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "CameraDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
