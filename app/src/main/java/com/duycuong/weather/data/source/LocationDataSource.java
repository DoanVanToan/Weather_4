package com.duycuong.weather.data.source;

import com.duycuong.weather.data.model.WeatherLocation;

import java.util.List;

/**
 * Created by DuyCương on 12/02/2018.
 */

public interface LocationDataSource {

    List<WeatherLocation> getLocationHistory();

    void addLocation(WeatherLocation locationn);

    void deleteLocation(WeatherLocation locationn);
}
