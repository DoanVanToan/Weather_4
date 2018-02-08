package com.duycuong.weather.data.source;

import android.location.Location;

import com.duycuong.weather.data.model.AddressResponse;
import com.duycuong.weather.data.model.CurrentlyResponse;

/**
 * Created by DuyCương on 06/02/2018.
 */

public class WeatherRepository implements WeatherDataSource {

    private WeatherDataSource mWeatherDataSource;

    public WeatherRepository(WeatherDataSource weatherDataSource) {
        mWeatherDataSource = weatherDataSource;
    }

    @Override
    public void getWeatherCurrent(Location location, Callback<CurrentlyResponse> callback) {
        mWeatherDataSource.getWeatherCurrent(location, callback);
    }

    @Override
    public void getAddress(Location location, Callback<AddressResponse> callback) {
        mWeatherDataSource.getAddress(location, callback);
    }
}
