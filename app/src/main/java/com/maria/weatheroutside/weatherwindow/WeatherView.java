package com.maria.weatheroutside.weatherwindow;

import com.maria.weatheroutside.model.entity.WeatherData;

public interface WeatherView {
    void close();
    
    void showWeather(WeatherData weatherData);

    void errorGetWeather(Throwable throwable);

    void requestPermission(String[] permissions, int requestCodePermission);

    void showMessage(int messageRes);
}
