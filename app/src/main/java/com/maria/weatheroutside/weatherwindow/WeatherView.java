package com.maria.weatheroutside.weatherwindow;

import com.maria.weatheroutside.model.entity.Weather;

public interface WeatherView {
    void close();
    
    void showWeather(Weather weather);

    void errorGetWeather(Throwable throwable);

    void requestPermissions(String[] permissions);
}
