package com.maria.weatheroutside.model.network;

import com.maria.weatheroutside.model.entity.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {
    //String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?")
    Observable<BaseResponse<Weather>> getWeather(
            @Query("lat") final double lat,
            @Query("lon") final double lon,
            @Query("appid") final String appId
    );
}
