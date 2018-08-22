package com.maria.weatheroutside.weatherwindow;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.maria.weatheroutside.model.network.Coordinates;
import com.maria.weatheroutside.model.repository.WeatherRepo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.LOCATION_SERVICE;

class WeatherPresenter {

    private WeatherView view;

    private Context context;

    private LocationManager locationManager;

    private final WeatherRepo weatherRepo = new WeatherRepo();

    WeatherPresenter(WeatherView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void onCreate() {
        checkLocationPermission();

        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (locationManager != null) {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                sendWeatherRequest(location);
            }
        }
    }

    public void exitBtnPressed() {
        view.close();
    }

    public void detachView() {
        view = null;
    }

    @SuppressLint("CheckResult")
    private void sendWeatherRequest(Location location) {
        Coordinates.setCoordinates(location.getLatitude(), location.getLongitude());

        weatherRepo.getWeather(location.getLatitude(), location.getLongitude())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showWeather, view::errorGetWeather);
    }

    private void checkLocationPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET};

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            view.requestPermissions(permissions);
        }
    }
}
