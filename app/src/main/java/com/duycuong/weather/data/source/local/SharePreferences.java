package com.duycuong.weather.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DuyCương on 22/02/2018.
 */

public class SharePreferences {
    private static final String UNIT = "unit";
    private static final String TEMPERATURE = "temperature";
    private static final String SPEED = "speed";
    public static final String CELSIUS = "C";
    public static final String FAHRENHEIT = "F";
    public static final String MS = "m/s";
    public static final String KMH = "km/h";

    private SharedPreferences mSharedPreferences;

    public SharePreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(UNIT, Context.MODE_PRIVATE);
    }

    public String getTemperature() {
        return mSharedPreferences.getString(TEMPERATURE, FAHRENHEIT);
    }

    public void putTemperature(String degree) {
        mSharedPreferences.edit().putString(TEMPERATURE, degree).apply();
    }

    public String getSpeed() {
        return mSharedPreferences.getString(SPEED, MS);
    }

    public void putSpeed(String speed) {
        mSharedPreferences.edit().putString(SPEED, speed).apply();
    }


}
