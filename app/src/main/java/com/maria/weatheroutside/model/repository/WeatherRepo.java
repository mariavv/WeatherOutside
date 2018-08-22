package com.maria.weatheroutside.model.repository;

import com.maria.weatheroutside.model.entity.Weather;
import com.maria.weatheroutside.model.network.BaseResponse;
import com.maria.weatheroutside.model.network.RestService;
import com.maria.weatheroutside.model.network.RestServiceProvider;

import io.reactivex.Observable;

public class WeatherRepo {

    private static final String APPID = "cdca3adac9b37c29f81148bf15bc2d55";

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    public Observable<Weather> getWeather(double lat, double lon) {
        return restService.getWeather(lat, lon, APPID).map(BaseResponse::getData);
    }
}
