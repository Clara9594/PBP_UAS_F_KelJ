package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarResponse {
    @SerializedName("data")
    @Expose
    private List<CarDAO> cars = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public List<CarDAO> getCars(){
        return cars;
    }

    public String getMessage(){
        return message;
    }

    public void setCars(List<CarDAO> cars) {
        this.cars = cars;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
