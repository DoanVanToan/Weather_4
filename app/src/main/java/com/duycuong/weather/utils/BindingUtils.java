package com.duycuong.weather.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by DuyCương on 01/02/2018.
 */

public class BindingUtils {

    @BindingAdapter({"bind:pagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"bind:pager"})
    public static void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter({"bind:iconWeather"})
    public static void loadImage(ImageView imageView, int idImage) {
        imageView.setImageResource(idImage);
    }

    @BindingAdapter({"bind:roundingTemperature"})
    public static void rounding(TextView textview, double temparature) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        textview.setText(df.format(temparature));
    }

    @BindingAdapter({"bind:onRefresh"})
    public static void setOnRefreshing(SwipeRefreshLayout layout,
                                       SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"bind:recyclerAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

}
