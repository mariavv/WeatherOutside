package com.maria.weatheroutside.model.network;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("main")
    protected T data;

    public T getData() {
        int r = 0;
        return data;
    }
}
