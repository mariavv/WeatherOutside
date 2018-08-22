package com.maria.weatheroutside.model.entity;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("temp")
    private String main = "";

    public Weather(String main) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }
}
