package com.duycuong.weather.ui.screen.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.duycuong.weather.R;
import com.duycuong.weather.databinding.ActivityMainBinding;

/**
 * Created by DuyCương on 04/02/2018.
 */

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int MIN_TIME = 0;
    private static final int MIN_DISTANCE = 0;

    private MainViewModel mViewModel;
    private LocationManager mLocationManager;
    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = getSupportActionBar();
        mViewModel = new MainViewModel(this);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mViewModel);
        getCurrentLocation();
    }

    @Override
    protected void onDestroy() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            registerLocationChanged();
        } else {
            showToast(R.string.title_permission_denied_explanation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocationManager.removeUpdates(this);
        mViewModel.getLocation(location);
        mViewModel.getAddress(location, mActionBar);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // no ops
    }

    @Override
    public void onProviderEnabled(String s) {
        // no ops
    }

    @Override
    public void onProviderDisabled(String s) {
        // no ops
    }

    @SuppressLint("NewApi")
    private void getCurrentLocation() {
        if (!isAllowAccessFineLocationPermission()) {
            return;
        }
        registerLocationChanged();
    }

    @SuppressLint("NewApi")
    private boolean isAllowAccessFineLocationPermission() {
        if (!isEnablePermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            return false;
        }
        return true;
    }

    private boolean isEnablePermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("MissingPermission")
    private void registerLocationChanged() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showToast(R.string.title_location_unavailable);
            return;
        }
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                this);
    }

    private void showToast(int msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
