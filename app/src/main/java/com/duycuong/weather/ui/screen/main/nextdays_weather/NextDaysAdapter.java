package com.duycuong.weather.ui.screen.main.nextdays_weather;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.duycuong.weather.R;
import com.duycuong.weather.data.model.Datum;
import com.duycuong.weather.databinding.ItemWeatherBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DuyCương on 09/02/2018.
 */

public class NextDaysAdapter extends
        RecyclerView.Adapter<NextDaysAdapter.NextDaysWeatherViewHolder> {

    private List<Datum> mDatums;

    public NextDaysAdapter() {
        mDatums = new ArrayList<>();
    }

    @Override
    public NextDaysWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWeatherBinding itemForecastBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_weather, parent, false);
        return new NextDaysWeatherViewHolder(itemForecastBinding);
    }

    @Override
    public void onBindViewHolder(NextDaysWeatherViewHolder holder, int position) {
        holder.setBinding(mDatums.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatums != null ? mDatums.size() : 0;
    }

    public void setDataAdapter(List<Datum> data) {
        if (data == null) {
            return;
        }
        mDatums.clear();
        for (int i = 1; i <= 7; i++) {
            mDatums.add(data.get(i));
        }
        notifyDataSetChanged();
    }

    public class NextDaysWeatherViewHolder extends RecyclerView.ViewHolder {

        private ItemWeatherBinding itemForecastBinding;

        public NextDaysWeatherViewHolder(ItemWeatherBinding itemForecastBinding) {
            super(itemForecastBinding.getRoot());
            this.itemForecastBinding = itemForecastBinding;
        }

        public void setBinding(Datum datum) {
            if (datum == null) {
                return;
            }
            itemForecastBinding.setViewModel(new ItemWeatherViewModel(datum));
        }
    }
}
