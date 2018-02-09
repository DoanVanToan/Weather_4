package com.duycuong.weather.ui.screen.main.current_weather;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.widget.Toast;

import com.duycuong.weather.BR;
import com.duycuong.weather.R;
import com.duycuong.weather.data.model.CurrentWeather;
import com.duycuong.weather.data.model.CurrentlyResponse;
import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.data.source.WeatherRepository;
import com.duycuong.weather.data.source.remote.WeatherRemoteDataSource;
import com.duycuong.weather.utils.Constant;
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
    private WeatherRepository mWeatherRepository;

    public CurrentWeatherViewModel(Context context) {
        mContext = context;
        mWeatherRepository = new WeatherRepository(new WeatherRemoteDataSource());
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
    public String getCurrenTime() {
        return mTime;
    }

    public void setCurrentTime(String time) {
        mTime = time;
        notifyPropertyChanged(BR.currenTime);
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

    public void getTheCurrentWeather(Location location) {
        mWeatherRepository.getWeatherCurrent(location,
                new WeatherDataSource.Callback<CurrentlyResponse>() {
                    @Override
                    public void onStartLoading() {
                        //no_ops
                    }

                    @Override
                    public void onGetSuccess(CurrentlyResponse currentlyResponse) {
                        if (currentlyResponse.getCurrently() != null) {
                            setCurrentTime(TimeUtils.getCurrentTime());
                            setIcon(getStringIcon(currentlyResponse.getCurrently().getIcon()));
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

    private int getStringIcon(String strIcon) {
        switch (strIcon) {
            case Constant.CLEAR_DAY:
                return R.drawable.sunny_day;
            case Constant.CLEAR_NIGHT:
                return R.drawable.clear_night;
            case Constant.RAIN:
                return R.drawable.rain;
            case Constant.SNOW:
                return R.drawable.snow;
            case Constant.SLEET:
                return R.drawable.snow;
            case Constant.WIND:
                return R.drawable.wind;
            case Constant.FOG:
                return R.drawable.fog;
            case Constant.CLOUDY:
                return R.drawable.cloudly;
            case Constant.PARTLY_CLOUDY_DAY:
                return R.drawable.partly_cloudy_day;
            case Constant.PARTLY_CLOUDY_NIGHT:
                return R.drawable.partly_cloudy_day;
            default:
                return R.drawable.defaut;
        }
    }
}
