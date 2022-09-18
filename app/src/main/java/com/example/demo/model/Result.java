package com.example.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("result")
    @Expose
    public Boolean result;
    @SerializedName("message")
    @Expose
    public String message;
}
