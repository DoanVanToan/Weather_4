package com.duycuong.weather.ui.screen.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.duycuong.weather.R;
import com.duycuong.weather.data.model.WeatherLocation;
import com.duycuong.weather.databinding.ActivityMainBinding;
import com.duycuong.weather.ui.screen.setting.SettingActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

/**
 * Created by DuyCương on 04/02/2018.
 */

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int MIN_TIME = 0;
    private static final int MIN_DISTANCE = 0;

    private MainViewModel mViewModel;
    private LocationManager mLocationManager;
    private ActionBar mActionBar;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;

    private Location mLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();

        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            findPlace();
            return true;
        }

        if (id == R.id.location) {
            // Todo location history
        }

        if (id == R.id.select_date) {
            //Todo select date
        }

        if (id == R.id.setting) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void findPlace() {
        try {

            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                    .build();

            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(typeFilter)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    mActionBar.setTitle(place.getName());

                    Location location = new Location(LocationManager.NETWORK_PROVIDER);
                    location.setLatitude(place.getLatLng().latitude);
                    location.setLongitude(place.getLatLng().longitude);

                    mViewModel.saveLocation(new WeatherLocation(String.valueOf(place.getName()),
                            place.getLatLng().latitude, place.getLatLng().longitude));

                    mViewModel.getLocation(location);
                    break;

                case PlaceAutocomplete.RESULT_ERROR:
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    Toast.makeText(this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    //no-ops
                    break;
            }
        }
    }

    private void showToast(int msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
