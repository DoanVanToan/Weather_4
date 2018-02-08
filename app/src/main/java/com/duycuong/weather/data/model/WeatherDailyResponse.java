package com.duycuong.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DuyCương on 06/02/2018.
 */

public class WeatherDailyResponse {
    @SerializedName("latitude")
    @Expose
    private double mLatitude;
    @SerializedName("longitude")
    @Expose
    private double mLongitude;
    @SerializedName("timezone")
    @Expose
    private String mTimezone;
    @SerializedName("daily")
    @Expose
    private DailyWeather mDaily;
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

    public DailyWeather getDaily() {
        return mDaily;
    }

    public void setDaily(DailyWeather daily) {
        mDaily = daily;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }
}
