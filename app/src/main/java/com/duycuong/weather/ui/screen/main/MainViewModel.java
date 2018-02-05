package com.duycuong.weather.ui.screen.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;

import com.duycuong.weather.BR;

/**
 * Created by DuyCương on 04/02/2018.
 */

public class MainViewModel extends BaseObservable {
    private MainActivity mActivity;
    private MainViewPagerAdapter mPagerAdapter;

    public MainViewModel(Context context) {
        mActivity = (MainActivity) context;
        mPagerAdapter = new MainViewPagerAdapter(context,
                mActivity.getSupportFragmentManager());
    }


    @Bindable
    public MainViewPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(MainViewPagerAdapter mainViewPagerAdapter) {
        mPagerAdapter = mainViewPagerAdapter;
        notifyPropertyChanged(BR.pagerAdapter);
    }

    public void getLocation(Location location) {
        mPagerAdapter.getLocation(location);
    }
}
