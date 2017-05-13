package com.code4piter.blueskythinking.megapp.model.dto;

public class RequestCameraListDto {
	private String search;

	private Integer distance;

    private Boolean sortDirection;

    public Boolean getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Boolean sortDirection) {
        this.sortDirection = sortDirection;
    }

    private Double DangerLevel;

	private Long latitude;

	private Long longitude;

	private String sortBy;

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

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
