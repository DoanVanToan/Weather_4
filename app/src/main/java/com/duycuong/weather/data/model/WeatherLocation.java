package com.duycuong.weather.data.model;

/**
 * Created by DuyCương on 11/02/2018.
 */

public class WeatherLocation {
    private String mName;
    private double mLatitude;
    private double mLongitude;

    public WeatherLocation(String name, double lat, double longitude) {
        mName = name;
        mLatitude = lat;
        mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }
}
