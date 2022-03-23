package com.mordor.app;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class BaseResponse<T> {
    @SerializedName("status")
    @Getter
    private String status;
    @SerializedName("message")
    @Getter
    private String message;
    @SerializedName("code")
    @Getter
    private String code;
    @SerializedName("data")
    @Getter
    private T data;
}
