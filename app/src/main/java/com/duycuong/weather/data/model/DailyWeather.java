package com.duycuong.weather.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DuyCương on 06/02/2018.
 */

public class DailyWeather {
    @SerializedName("summary")
    @Expose
    private String mSummary;
    @SerializedName("icon")
    @Expose
    private String mIcon;
    @SerializedName("data")
    @Expose
    private List<Datum> mDatums;

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public List<Datum> getDatums() {
        return mDatums;
    }

    public void setDatums(List<Datum> datums) {
        mDatums = datums;
    }
}
