package com.maria.weatheroutside.model.network;

import com.maria.weatheroutside.model.entity.WeatherData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?")
    Call<WeatherData> getWeatherCall(
            @Query("lat") final double lat,
            @Query("lon") final double lon,
            @Query("appid") final String appId,
            @Query("units") String units,
            @Query("lang") String lang);
}
