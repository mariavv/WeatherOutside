package com.maria.weatheroutside.weatherwindow;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.maria.weatheroutside.R;
import com.maria.weatheroutside.model.entity.WeatherData;
import com.maria.weatheroutside.model.network.BaseResponse;
import com.maria.weatheroutside.model.repository.WeatherRepo;

import static android.content.Context.LOCATION_SERVICE;

class WeatherPresenter implements WeatherRepo.Listener {

    private static final int REQUEST_CODE_PERMISSION = 1;

    private WeatherView view;

    private Context context;

    private final WeatherRepo weatherRepo = new WeatherRepo(this);

    WeatherPresenter(WeatherView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void onStart() {
        sync();
    }

    public void sync() {
        if (haveLocationPermission()) {
            getWeather();
        }
        ;
    }

    public void exitBtnPressed() {
        view.close();
    }

    public void detachView() {
        view = null;
    }

    @Override
    public void onResponse(BaseResponse<WeatherData> weatherData) {
        view.showWeather(weatherData.getData());
    }

    @Override
    public void onFailure(Throwable t) {
        view.errorGetWeather(t);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                for (int grant : grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        view.errorGetWeather(new Throwable(context.getString(R.string.permissions_not_granted)));
                        view.close();
                        return;
                    }
                }
                getWeather();
        }
    }

    private void getWeather() {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (locationManager != null) {
            @SuppressLint("MissingPermission")
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                sendWeatherRequest(location);
            }
        }

    }

    @SuppressLint("CheckResult")
    private void sendWeatherRequest(Location location) {
        weatherRepo.getCurrentWeather(location.getLatitude(), location.getLongitude(),
                context.getString(R.string.measuring_system), context.getString(R.string.locale));
    }

    private boolean haveLocationPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET};

        for (String permission : permissions) {
            if (checkPermission(permission)) {
                view.requestPermission(permissions, REQUEST_CODE_PERMISSION);
                return false;
            }
        }

        return true;
    }

    private boolean checkPermission(String permission) {
        return getPermissionState(permission) != PackageManager.PERMISSION_GRANTED;
    }

    private int getPermissionState(String permission) {
        return ActivityCompat.checkSelfPermission(context, permission);
    }
}
