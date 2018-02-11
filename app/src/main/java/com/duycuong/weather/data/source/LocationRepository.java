package com.duycuong.weather.data.source;

import android.content.Context;

import com.duycuong.weather.data.model.WeatherLocation;
import com.duycuong.weather.data.source.local.sqlite.LocationLocalDataSource;

import java.util.List;

/**
 * Created by DuyCương on 12/02/2018.
 */

public class LocationRepository implements LocationDataSource {
    private LocationDataSource mLocationDataSource;

    public static LocationRepository getInstance(Context context) {
        return new LocationRepository(LocationLocalDataSource.getInstance(context));
    }

    public LocationRepository(LocationDataSource locationDataSource) {
        mLocationDataSource = locationDataSource;
    }


    @Override
    public List<WeatherLocation> getLocationHistory() {
        return mLocationDataSource.getLocationHistory();
    }

    @Override
    public void addLocation(WeatherLocation locationn) {
        mLocationDataSource.addLocation(locationn);
    }

    @Override
    public void deleteLocation(WeatherLocation locationn) {
        mLocationDataSource.deleteLocation(locationn);
    }
}
