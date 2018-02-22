package com.duycuong.weather.ui.screen.main.current_weather;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.duycuong.weather.BR;
import com.duycuong.weather.R;
import com.duycuong.weather.data.model.CurrentWeather;
import com.duycuong.weather.data.model.CurrentlyResponse;
import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.data.source.WeatherRepository;
import com.duycuong.weather.data.source.local.SharePreferences;
import com.duycuong.weather.data.source.remote.WeatherRemoteDataSource;
import com.duycuong.weather.utils.Constant;
import com.duycuong.weather.utils.IconWeather;
import com.duycuong.weather.utils.RoundingUtils;
import com.duycuong.weather.utils.TimeUtils;

/**
 * Created by DuyCương on 08/02/2018.
 */

public class CurrentWeatherViewModel extends BaseObservable {
    private CurrentWeather mCurrentWeather;
    private String mTime;
    private int mIcon;
    private boolean mShowProgressBar;
    private Context mContext;
    private boolean mIsRefresh;
    private WeatherRepository mWeatherRepository;
    private Location mLocation;

    private SharePreferences mSharePreferences;

    private String mCurrentTemperature;
    private String mWindSpeed;
    private double mTemp;
    private double mSpeed;

    public CurrentWeatherViewModel(Context context) {
        mContext = context;
        mWeatherRepository = new WeatherRepository(new WeatherRemoteDataSource());
        mSharePreferences = new SharePreferences(context);
        setShowProgressBar(true);
    }

    @Bindable
    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
        notifyPropertyChanged(BR.currentWeather);
    }

    @Bindable
    public String getCurrentTemperature() {
        return mCurrentTemperature;
    }

    public void setCurrentTemperature(String temperature) {

        String degree = mSharePreferences.getTemperature();
        if (degree.equals(SharePreferences.FAHRENHEIT)) {
            mCurrentTemperature = RoundingUtils.roundingTemperature(Double.valueOf(temperature));
        } else {
            mCurrentTemperature = RoundingUtils.roundingTemperature(
                    (Double.parseDouble(temperature) - 32) * 5 / 9);
        }
        notifyPropertyChanged(BR.currentTemperature);
    }

    @Bindable
    public String getSpeed() {
        return mWindSpeed;
    }

    public void setSpeed(String speed) {
        String speedUnit = mSharePreferences.getSpeed();
        if (speedUnit.equals(SharePreferences.MS)) {
            mWindSpeed = speed + SharePreferences.MS;
        } else {
            mWindSpeed = RoundingUtils.roundingSpeed(
                    Double.parseDouble(speed) / 1000f) + SharePreferences.KMH;
        }
        notifyPropertyChanged(BR.speed);
    }

    @Bindable
    public String getCurrentTime() {
        return mTime;
    }

    public void setCurrentTime(String time) {
        mTime = time;
        notifyPropertyChanged(BR.currentTime);
    }

    @Bindable
    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int idIcon) {
        mIcon = idIcon;
        notifyPropertyChanged(BR.icon);
    }

    @Bindable
    public boolean isShowProgressBar() {
        return mShowProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        mShowProgressBar = showProgressBar;
        notifyPropertyChanged(BR.showProgressBar);
    }

    @Bindable
    public boolean isRefresh() {
        return mIsRefresh;
    }

    public void setRefresh(boolean refresh) {
        mIsRefresh = refresh;
        notifyPropertyChanged(BR.refresh);
    }

    public void getTheCurrentWeather(Location location) {
        mLocation = location;
        mWeatherRepository.getWeatherCurrent(location,
                new WeatherDataSource.Callback<CurrentlyResponse>() {
                    @Override
                    public void onStartLoading() {
                        setShowProgressBar(true);
                    }

                    @Override
                    public void onGetSuccess(CurrentlyResponse currentlyResponse) {
                        if (currentlyResponse.getCurrently() != null) {
                            setCurrentTime(TimeUtils.covertTime(Constant.HH_MM_DD_MM_YYYY,
                                    currentlyResponse.getCurrently().getTime()));
                            setIcon(IconWeather.getStringIcon(currentlyResponse
                                    .getCurrently().getIcon()));
                            mTemp = currentlyResponse.getCurrently().getTemperature();
                            mSpeed = currentlyResponse.getCurrently().getWindSpeed();
                            setCurrentTemperature(String.valueOf(mTemp));
                            setSpeed(String.valueOf(mSpeed));
                            setCurrentWeather(currentlyResponse.getCurrently());
                        } else {
                            Toast.makeText(mContext, mContext.getString(R.string.title_no_data),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onGetFailure(String message) {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        setShowProgressBar(false);
                    }
                });
    }

    public void updateUnit() {
        setCurrentTemperature(String.valueOf(mTemp));
        setSpeed(String.valueOf(mSpeed));
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefresh(true);
                if (mLocation != null) {
                    getTheCurrentWeather(mLocation);
                }
                setRefresh(false);
            }
        };
    }


}
