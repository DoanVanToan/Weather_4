package com.duycuong.weather.data.source.remote;

import android.os.AsyncTask;

import com.duycuong.weather.data.source.WeatherDataSource;
import com.duycuong.weather.utils.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DuyCương on 03/01/2018.
 */

public class LoadDataAsync extends AsyncTask<String, String, String> {
    private WeatherDataSource.Callback<String> mCallback;

    public LoadDataAsync(WeatherDataSource.Callback<String> callback) {
        mCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        mCallback.onStartLoading();
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlStr = strings[0];
        String result = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(Constant.CONNECT_TIMEOUT);
            connection.setReadTimeout(Constant.CONNECT_TIMEOUT);
            connection.setRequestMethod(Constant.GET_METHOD);
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException(Constant.HTTP_CODE + responseCode);
            }
            InputStream inputStream = connection.getInputStream();
            result = readStream(inputStream);
        } catch (MalformedURLException e) {
            mCallback.onGetFailure(e.getMessage());
        } catch (IOException e) {
            mCallback.onGetFailure(e.getMessage());
        }
        return result;
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream, Constant.UTF_8));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } finally {
            inputStream.close();
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onGetSuccess(result);
        mCallback.onComplete();
    }
}
