package com.duycuong.weather.ui.screen.main;

import android.content.Context;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duycuong.weather.R;
import com.duycuong.weather.ui.screen.main.current_weather.CurrentWeatherFragment;

/**
 * Created by DuyCương on 01/02/2018.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter
        implements ListenerLocationChange {
    private Fragment[] mFragments;
    private String[] mFragmentNames;
    private CurrentWeatherFragment mCurrentWeatherFragment
            = CurrentWeatherFragment.newInstance();
//    private NextDaysWeatherFragment mNextsDayWeatherFragment
//            = NextDaysWeatherFragment.newInstance();

    public MainViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mFragments = new Fragment[]{
                mCurrentWeatherFragment//, mNextsDayWeatherFragment
        };
        mFragmentNames = new String[]{
                context.getString(R.string.title_today), context.getString(R.string.title_senvenday)
        };
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentNames[position];
    }

    @Override
    public void getLocation(Location location) {
        mCurrentWeatherFragment.getLocation(location);
    }
}
