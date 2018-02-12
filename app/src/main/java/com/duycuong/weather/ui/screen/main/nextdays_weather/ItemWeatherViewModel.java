package com.duycuong.weather.ui.screen.main.nextdays_weather;

import android.databinding.BaseObservable;

import com.duycuong.weather.data.model.Datum;
import com.duycuong.weather.utils.Constant;
import com.duycuong.weather.utils.IconWeather;
import com.duycuong.weather.utils.TimeUtils;

/**
 * Created by DuyCương on 09/02/2018.
 */

public class ItemWeatherViewModel extends BaseObservable {

    private Datum mDailyWeather;

    ItemWeatherViewModel(Datum dailyWeather) {
        mDailyWeather = dailyWeather;
    }

    public String getDate() {
        return TimeUtils.covertTime(Constant.DAY_MONTH, mDailyWeather.getTime());
    }

    public String getSummary() {
        return mDailyWeather.getSummary();
    }

    public double getTemperatureHight() {
        return mDailyWeather.getTemperatureHigh();
    }

    public double getTemperatureLow() {
        return mDailyWeather.getTemperatureLow();
    }

    public int getIcon() {
        return IconWeather.getStringIcon(mDailyWeather.getIcon());
    }
}
