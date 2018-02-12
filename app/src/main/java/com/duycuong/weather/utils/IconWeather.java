package com.duycuong.weather.utils;

import com.duycuong.weather.R;

/**
 * Created by DuyCương on 12/02/2018.
 */

public class IconWeather {
    public static int getStringIcon(String strIcon) {
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
