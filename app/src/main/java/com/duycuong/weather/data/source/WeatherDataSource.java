package com.duycuong.weather.data.source;

import android.location.Location;

import com.duycuong.weather.data.model.AddressResponse;
import com.duycuong.weather.data.model.CurrentlyResponse;

/**
 * Created by DuyCương on 05/02/2018.
 */

public interface WeatherDataSource {
    interface Callback<T> {
        void onStartLoading();

        void onGetSuccess(T data);

        void onGetFailure(String message);

        void onComplete();
    }

    void getWeatherCurrent(Location location, Callback<CurrentlyResponse> callback);

    void getAddress(Location location, Callback<AddressResponse> callback);
}
