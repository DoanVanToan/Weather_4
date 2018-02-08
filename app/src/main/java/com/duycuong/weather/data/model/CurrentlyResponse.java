package com.duycuong.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DuyCương on 05/02/2018.
 */

public class CurrentlyResponse {
    @SerializedName("latitude")
    @Expose
    private double mLatitude;
    @SerializedName("longitude")
    @Expose
    private double mLongitude;
    @SerializedName("timezone")
    @Expose
    private String mTimezone;
    @SerializedName("currently")
    @Expose
    private CurrentWeather mCurrently;
    @SerializedName("offset")
    @Expose
    private int mOffset;

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

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public CurrentWeather getCurrently() {
        return mCurrently;
    }

    public void setCurrently(CurrentWeather currently) {
        mCurrently = currently;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }
}
