package com.code4piter.blueskythinking.megapp.model.dto;

public class CameraDetailDto {

	private String name;

	private String address;

	private Double dangerLevel;

	private String imageBase64;

	private String videoLink;

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

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
}
