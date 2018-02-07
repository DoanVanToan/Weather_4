package com.duycuong.weather.ui.screen.main.nextsday_weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duycuong.weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextsDayWeatherFragment extends Fragment {

    public static NextsDayWeatherFragment newInstance() {
        return new NextsDayWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seven_day_weather, container, false);
        return view;
    }
}
