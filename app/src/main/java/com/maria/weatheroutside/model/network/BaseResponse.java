package com.maria.weatheroutside.model.network;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("")

    protected T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
