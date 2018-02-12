package com.duycuong.weather.data.source.remote;

import android.content.res.Resources;
import android.location.Location;

import com.duycuong.weather.BuildConfig;
import com.duycuong.weather.R;
import com.duycuong.weather.data.model.AddressResponse;
import com.duycuong.weather.data.model.CurrentlyResponse;
import com.duycuong.weather.data.model.WeatherDailyResponse;
import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Created by DuyCương on 06/02/2018.
 */

public class WeatherRemoteDataSource implements WeatherDataSource {

    @Override
    public void getWeatherCurrent(Location location, final Callback<CurrentlyResponse> callback) {
        StringBuilder url =
                new StringBuilder(Constant.DARK_SKY_API)
                        .append("/")
                        .append(Constant.FORECAST_PART)
                        .append("/")
                        .append(BuildConfig.DASK_SKY_API_KEY)
                        .append("/")
                        .append(location.getLatitude() + "")
                        .append(",")
                        .append(location.getLongitude())
                        .append("?")
                        .append(Constant.EXCLUDE_PART)
                        .append("=")
                        .append(Constant.MINUTELY_PART)
                        .append(",")
                        .append(Constant.HOURLY_PART)
                        .append(",")
                        .append(Constant.ALERTS_PART)
                        .append(",")
                        .append(Constant.FLAGS_PART)
                        .append(",")
                        .append(Constant.DAILY_PART);

        new LoadDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.title_load_failure));
                        return;
                    }

                    Gson gson = new Gson();
                    callback.onGetSuccess(gson.fromJson(data, CurrentlyResponse.class));
                } catch (JsonParseException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(url.toString());
    }

    @Override
    public void getAddress(Location location, final Callback<AddressResponse> callback) {
        StringBuilder url =
                new StringBuilder(Constant.GEO_CODE_API)
                        .append("?")
                        .append(Constant.LATLNG_PART)
                        .append("=")
                        .append(location.getLatitude() + "")
                        .append(",")
                        .append(location.getLongitude() + "")
                        .append("&")
                        .append(Constant.KEY_PART)
                        .append("=")
                        .append(BuildConfig.GEOCODE_API_KEY);
        new LoadDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.title_load_failure));
                        return;
                    }

                    Gson gson = new Gson();
                    callback.onGetSuccess(gson.fromJson(data, AddressResponse.class));
                } catch (JsonParseException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(url.toString());

    }

    @Override
    public void getNextDaysWeather(Location location,
                                   final Callback<WeatherDailyResponse> callback) {
        StringBuilder url =
                new StringBuilder(Constant.DARK_SKY_API)
                        .append("/")
                        .append(Constant.FORECAST_PART)
                        .append("/")
                        .append(BuildConfig.DASK_SKY_API_KEY)
                        .append("/")
                        .append(location.getLatitude())
                        .append(",")
                        .append(location.getLongitude())
                        .append("?")
                        .append(Constant.EXCLUDE_PART)
                        .append("=")
                        .append(Constant.MINUTELY_PART)
                        .append(",")
                        .append(Constant.HOURLY_PART)
                        .append(",")
                        .append(Constant.ALERTS_PART)
                        .append(",")
                        .append(Constant.FLAGS_PART);

        new LoadDataAsync(new Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                try {
                    if (data == null) {
                        callback.onGetFailure(
                                Resources.getSystem().getString(R.string.title_load_failure));
                        return;
                    }

                    Gson gson = new Gson();
                    callback.onGetSuccess(gson.fromJson(data, WeatherDailyResponse.class));
                } catch (JsonParseException e) {
                    callback.onGetFailure(e.getMessage());
                }
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(url.toString());
    }
}
