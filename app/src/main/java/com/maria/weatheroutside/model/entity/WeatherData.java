package com.maria.weatheroutside.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("dt")
    private String dt;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private String cod;

    public WeatherData(List<Weather> weather, Main main, String dt, String id, String name, String cod) {
        this.weather = weather;
        this.main = main;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCod() {
        return cod;
    }
}
