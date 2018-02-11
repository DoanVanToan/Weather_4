package com.duycuong.weather.data.source.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.duycuong.weather.data.model.WeatherLocation;
import com.duycuong.weather.data.source.LocationDataSource;
import com.duycuong.weather.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DuyCương on 11/02/2018.
 */

public class LocationLocalDataSource extends SQLiteOpenHelper implements LocationDataSource {

    private static LocationLocalDataSource sLocationDataSource;

    public static LocationLocalDataSource getInstance(Context context) {
        if (sLocationDataSource == null) {
            sLocationDataSource = new LocationLocalDataSource(context);
        }
        return sLocationDataSource;
    }

    public LocationLocalDataSource(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE "
                + Constant.TABLE_LOACTION + " ("
                + Constant.NAME_LOCATION + " TEXT primary key, "
                + Constant.LATITUDE + " REAL ,"
                + Constant.LONGITUDE + " REAL )";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Constant.TABLE_LOACTION;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    @Override
    public List<WeatherLocation> getLocationHistory() {
        List<WeatherLocation> locations = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(Constant.TABLE_LOACTION,
                null, null, null,
                null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    WeatherLocation locationn = new WeatherLocation("", 0f, 0f);
                    locationn.setName(cursor.getString(
                            cursor.getColumnIndex(Constant.NAME_LOCATION)));
                    locationn.setLatitude(cursor.getDouble(
                            cursor.getColumnIndex(Constant.LATITUDE)));
                    locationn.setLongitude(cursor.getDouble(
                            cursor.getColumnIndex(Constant.LONGITUDE)));
                    locations.add(locationn);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            database.close();
        }
        return locations;
    }

    @Override
    public void addLocation(WeatherLocation locationn) {
        if (locationn == null) {
            return;
        }
        if (!checkLocationExist(locationn.getName())) {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.NAME_LOCATION, locationn.getName());
            contentValues.put(Constant.LATITUDE, locationn.getLatitude());
            contentValues.put(Constant.LONGITUDE, locationn.getLongitude());
            database.insert(Constant.TABLE_LOACTION, null, contentValues);
            database.close();
        }
    }

    @Override
    public void deleteLocation(WeatherLocation locationn) {
        if (locationn == null) {
            return;
        }
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Constant.TABLE_LOACTION, Constant.NAME_LOCATION + " = ?",
                new String[]{locationn.getName()});
        database.close();
    }

    public boolean checkLocationExist(String name) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(Constant.TABLE_LOACTION,
                new String[]{Constant.NAME_LOCATION}, Constant.NAME_LOCATION + "=?",
                new String[]{String.valueOf(name)},
                null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }
}
