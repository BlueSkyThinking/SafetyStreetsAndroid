package com.code4piter.blueskythinking.megapp.model.dto;

public class CameraDetailDto {

	private String name;

	private String address;

	private Double dangerLevel;

	private String image;

	private String videoAndroid;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideoAndroid() {
		return videoAndroid;
	}

	public void setVideoAndroid(String videoAndroid) {
		this.videoAndroid = videoAndroid;
	}

	@Override
	public String toString() {
		return "CameraDetailDto{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", dangerLevel=" + dangerLevel +
				", image='" + image + '\'' +
				", videoAndroid='" + videoAndroid + '\'' +
				'}';
	}
}
