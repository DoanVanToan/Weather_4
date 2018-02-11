package com.duycuong.weather.ui.screen.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.duycuong.weather.BR;
import com.duycuong.weather.data.model.AddressResponse;
import com.duycuong.weather.data.model.WeatherLocation;
import com.duycuong.weather.data.source.LocationRepository;
import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.data.source.WeatherRepository;
import com.duycuong.weather.data.source.local.sqlite.LocationLocalDataSource;
import com.duycuong.weather.data.source.remote.WeatherRemoteDataSource;


/**
 * Created by DuyCương on 04/02/2018.
 */

public class MainViewModel extends BaseObservable {
    private MainActivity mActivity;
    private MainViewPagerAdapter mPagerAdapter;
    private WeatherRepository mWeatherRepository;
    private LocationRepository mLocationRepository;

    public MainViewModel(Context context) {
        mActivity = (MainActivity) context;
        mPagerAdapter = new MainViewPagerAdapter(context,
                mActivity.getSupportFragmentManager());
        mWeatherRepository = new WeatherRepository(new WeatherRemoteDataSource());
        mLocationRepository = new LocationRepository(new LocationLocalDataSource(context));
    }

    @Bindable
    public MainViewPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(MainViewPagerAdapter mainViewPagerAdapter) {
        mPagerAdapter = mainViewPagerAdapter;
        notifyPropertyChanged(BR.pagerAdapter);
    }

    public void getLocation(Location location) {
        mPagerAdapter.getLocation(location);
    }

    public void getAddress(Location location, final ActionBar actionBar) {
        mWeatherRepository.getAddress(location, new WeatherDataSource.Callback<AddressResponse>() {
            @Override
            public void onStartLoading() {
                //no-ops
            }

            @Override
            public void onGetSuccess(AddressResponse addressResponse) {
                String address = addressResponse.getResults().get(0).getAddressComponents()
                        .get(2).getLongName();
                actionBar.setTitle(address);
            }

            @Override
            public void onGetFailure(String message) {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                //no-ops
            }
        });
    }

    public void saveLocation(WeatherLocation location) {
        mLocationRepository.addLocation(location);
    }
}
