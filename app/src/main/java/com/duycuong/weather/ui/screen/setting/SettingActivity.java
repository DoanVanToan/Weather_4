package com.duycuong.weather.ui.screen.setting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.duycuong.weather.R;
import com.duycuong.weather.databinding.ActivitySettingBinding;

/**
 * Created by DuyCương on 21/02/2018.
 */
public class SettingActivity extends AppCompatActivity {
    private SettingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_setting);
        mViewModel = new SettingViewModel(this);
        binding.setViewModel(mViewModel);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getString(R.string.title_setting));
        actionbar.setDisplayHomeAsUpEnabled(true);
    }
}
