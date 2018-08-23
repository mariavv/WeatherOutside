package com.maria.weatheroutside.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("dt")
    private Integer dt;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private Integer cod;

    public WeatherData(List<Weather> weather, Main main, Integer dt, Integer id, String name, Integer cod) {
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

    public Integer getDt() {
        return dt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }
}
