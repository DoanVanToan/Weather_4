package com.duycuong.weather.ui.screen.main.nextdays_weather;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duycuong.weather.R;
import com.duycuong.weather.databinding.FragmentNextdaysWeatherBinding;
import com.duycuong.weather.ui.screen.main.ListenerLocationChange;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextDaysWeatherFragment extends Fragment implements ListenerLocationChange {
    private NextDaysViewModel mViewModel;

    public static NextDaysWeatherFragment newInstance() {
        return new NextDaysWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNextdaysWeatherBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_nextdays_weather, container, false);
        mViewModel = new NextDaysViewModel(getContext());
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void getLocation(Location location) {
        mViewModel.getDataWeatherDaily(location);
    }
}
