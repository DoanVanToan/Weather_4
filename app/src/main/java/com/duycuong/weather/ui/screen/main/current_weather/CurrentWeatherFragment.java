package com.duycuong.weather.ui.screen.main.current_weather;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duycuong.weather.R;
import com.duycuong.weather.databinding.FragmentCurrentWeatherBinding;
import com.duycuong.weather.ui.screen.main.ListenerLocationChange;

/**
 * A simple {@link Fragment} subclass.
 */

public class CurrentWeatherFragment extends Fragment implements ListenerLocationChange {
    private CurrentWeatherViewModel mViewModel;

    public static CurrentWeatherFragment newInstance() {
        return new CurrentWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCurrentWeatherBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_current_weather, container, false);
        mViewModel = new CurrentWeatherViewModel(getContext());
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void getLocation(Location location) {
        mViewModel.getTheCurrentWeather(location);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.updateUnit();
    }
}
