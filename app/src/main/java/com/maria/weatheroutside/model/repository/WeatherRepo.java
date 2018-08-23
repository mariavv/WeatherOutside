package com.maria.weatheroutside.model.repository;

import android.support.annotation.NonNull;

import com.maria.weatheroutside.model.entity.WeatherData;
import com.maria.weatheroutside.model.network.BaseResponse;
import com.maria.weatheroutside.model.network.RestService;
import com.maria.weatheroutside.model.network.RestServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepo {

    private static final String APPID = "cdca3adac9b37c29f81148bf15bc2d55";

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    private Listener listener;

    public WeatherRepo(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onResponse(BaseResponse<WeatherData> weatherData);

        void onFailure(Throwable t);
    }

    public void getCurrentWeather(double lat, double lon, String units, String lang) {
        Call<WeatherData> currentWeatherCall = restService.getWeatherCall(lat, lon, APPID, units, lang);

        currentWeatherCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                listener.onResponse(new BaseResponse<>(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
