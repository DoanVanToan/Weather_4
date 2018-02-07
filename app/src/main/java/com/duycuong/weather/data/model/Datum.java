package com.duycuong.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DuyCương on 05/02/2018.
 */

public class Datum {

    @SerializedName("time")
    @Expose
    private int mTime;

    @SerializedName("icon")
    @Expose
    private String mIcon;

    @SerializedName("temperatureHigh")
    @Expose
    private double mTemperatureHigh;

    @SerializedName("temperatureLow")
    @Expose
    private double mTemperatureLow;

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public double getTemperatureHigh() {
        return mTemperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        mTemperatureHigh = temperatureHigh;
    }

    public double getTemperatureLow() {
        return mTemperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        mTemperatureLow = temperatureLow;
    }
}
