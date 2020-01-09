package com.example.bike;

public class IncidentBean {
    private String type;

    private Double latitude;

    private Double longitude;

    IncidentBean(String type, Double latitude, Double longitude) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public String getType() {
        return this.type;
    }
}
