package com.duycuong.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DuyCương on 08/02/2018.
 */

public class TimeUtils {
    public static String covertTime(String format, long time) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(time * 1000);
    }
}

