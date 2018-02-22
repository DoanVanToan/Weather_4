package com.duycuong.weather.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by DuyCương on 22/02/2018 .
 */

public class RoundingUtils {
    public static String roundingTemperature(double temparature) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        return (df.format(temparature) + (char) 0x00B0);
    }

    public static String roundingSpeed(double temparature) {
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        return (df.format(temparature));
    }
}
