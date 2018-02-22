package com.duycuong.weather.ui.screen.setting;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.duycuong.weather.BR;
import com.duycuong.weather.data.source.local.SharePreferences;

/**
 * Created by DuyCương on 21/02/2018.
 */

public class SettingViewModel extends BaseObservable {
    private String mTemperatureUnit;
    private String mSpeedUnit;
    private String mTemperature;
    private String mSpeed;
    private SharePreferences mSharePreferences;

    public SettingViewModel(Context context) {
        mSharePreferences = new SharePreferences(context);
        mTemperature = mSharePreferences.getTemperature();
        mSpeed = mSharePreferences.getSpeed();

        setTemperatureUnit(mTemperature);
        setSpeedUnit(mSpeed);
    }

    @Bindable
    public String getTemperatureUnit() {
        return mTemperatureUnit;
    }

    public void setTemperatureUnit(String temperature) {
        mTemperatureUnit = temperature;
        notifyPropertyChanged(BR.temperatureUnit);
    }

    @Bindable
    public String getSpeedUnit() {
        return mSpeedUnit;
    }

    public void setSpeedUnit(String speedUnit) {
        mSpeedUnit = speedUnit;
        notifyPropertyChanged(BR.speedUnit);
    }

    public void onClickTemperature(View view) {

        mTemperature = mSharePreferences.getTemperature();

        if (mTemperature.equals(SharePreferences.FAHRENHEIT)) {
            mSharePreferences.putTemperature(SharePreferences.CELSIUS);
            setTemperatureUnit(SharePreferences.CELSIUS);
        } else if (mTemperature.equals(SharePreferences.CELSIUS)) {
            mSharePreferences.putTemperature(SharePreferences.FAHRENHEIT);
            setTemperatureUnit(SharePreferences.FAHRENHEIT);
        }

    }

    public void onClickSpeed(View view) {
        mSpeed = mSharePreferences.getSpeed();
        if (mSpeed.equals(SharePreferences.MS)) {
            mSharePreferences.putSpeed(SharePreferences.KMH);
            setSpeedUnit(SharePreferences.KMH);
        } else if (mSpeed.equals(SharePreferences.KMH)) {
            mSharePreferences.putSpeed(SharePreferences.MS);
            setSpeedUnit(SharePreferences.MS);
        }
    }
}
