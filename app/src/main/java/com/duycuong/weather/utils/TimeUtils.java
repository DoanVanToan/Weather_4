package com.duycuong.weather.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DuyCương on 08/02/2018.
 */

public class TimeUtils {
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(date.getTime());
    }
}
