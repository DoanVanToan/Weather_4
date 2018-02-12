package com.duycuong.weather.ui.screen.main.nextdays_weather;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.Toast;

import com.duycuong.weather.BR;
import com.duycuong.weather.data.model.WeatherDailyResponse;
import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.data.source.WeatherRepository;
import com.duycuong.weather.data.source.remote.WeatherRemoteDataSource;

/**
 * Created by DuyCương on 09/02/2018.
 */

public class NextDaysViewModel extends BaseObservable {
    private Context mContext;
    private NextDaysAdapter mNextDaysAdapter;
    private WeatherRepository mWeatherRepository;

    public NextDaysViewModel(Context context) {
        mContext = context;
        mNextDaysAdapter = new NextDaysAdapter();
        mWeatherRepository = new WeatherRepository(new WeatherRemoteDataSource());
    }

    public void getDataWeatherDaily(Location location) {
        mWeatherRepository.getNextDaysWeather(location,
                new WeatherDataSource.Callback<WeatherDailyResponse>() {
                    @Override
                    public void onStartLoading() {
                        //no-ops
                    }

                    @Override
                    public void onGetSuccess(WeatherDailyResponse data) {
                        mNextDaysAdapter.setDataAdapter(data.getDaily().getDatums());
                        setNextDaysAdapter(mNextDaysAdapter);
                    }

                    @Override
                    public void onGetFailure(String message) {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //no-ops
                    }
                });
    }

    @Bindable
    public NextDaysAdapter getNextDaysAdapter() {
        return mNextDaysAdapter;
    }

    public void setNextDaysAdapter(NextDaysAdapter nextDaysAdapter) {
        mNextDaysAdapter = nextDaysAdapter;
        notifyPropertyChanged(BR.nextDaysAdapter);
    }
}
