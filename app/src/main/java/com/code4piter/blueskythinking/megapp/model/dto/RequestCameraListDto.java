package com.code4piter.blueskythinking.megapp.model.dto;

public class RequestCameraListDto {
	private String search;

	private Integer distance;

	private Boolean sortDirection;
	private Double DangerLevel;
	private Double latitude;
	private Double longitude;
	private String sortBy;

	public Boolean getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Boolean sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Double getDangerLevel() {
		return DangerLevel;
	}

	public void setDangerLevel(Double dangerLevel) {
		DangerLevel = dangerLevel;
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

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
