package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BookingDAO {
    @SerializedName("id")
    public int id;

    @SerializedName("id_Pelanggan")
    public int id_Pelanggan;

    @SerializedName("name")
    public String name;

    @SerializedName("pick_Up_Location")
    public String pick_Up_Location;

    @SerializedName("pick_Up_Date")
    public String pick_Up_Date;

    @SerializedName("drop_Off_Date")
    public String drop_Off_Date;

    @SerializedName("pick_Up_Time")
    public String pick_Up_Time;

    @SerializedName("drop_Off_Time")
    public String drop_Off_Time;

    @SerializedName("car_Name")
    public String car_Name;

    @SerializedName("plat_nomor")
    public String plat_nomor;

    @SerializedName("driver_Age")
    public String driver_Age;

    @SerializedName("total")
    public int total;

    @SerializedName("status")
    public String status;

    public BookingDAO(int id, int id_Pelanggan, String name, String pick_Up_Location,
                      String pick_Up_Date, String drop_Off_Date, String pick_Up_Time,
                      String drop_Off_Time, String car_Name, String plat_nomor, String driver_Age,
                      int total, String status) {
        this.id = id;
        this.id_Pelanggan = id_Pelanggan;
        this.name = name;
        this.pick_Up_Location = pick_Up_Location;
        this.pick_Up_Date = pick_Up_Date;
        this.drop_Off_Date = drop_Off_Date;
        this.pick_Up_Time = pick_Up_Time;
        this.drop_Off_Time = drop_Off_Time;
        this.car_Name = car_Name;
        this.plat_nomor = plat_nomor;
        this.driver_Age = driver_Age;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Pelanggan() {
        return id_Pelanggan;
    }

    public void setId_Pelanggan(int id_Pelanggan) {
        this.id_Pelanggan = id_Pelanggan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPick_Up_Location() {
        return pick_Up_Location;
    }

    public void setPick_Up_Location(String pick_Up_Location) {
        this.pick_Up_Location = pick_Up_Location;
    }

    public String getPick_Up_Date() {
        return pick_Up_Date;
    }

    public void setPick_Up_Date(String pick_Up_Date) {
        this.pick_Up_Date = pick_Up_Date;
    }

    public String getDrop_Off_Date() {
        return drop_Off_Date;
    }

    public void setDrop_Off_Date(String drop_Off_Date) {
        this.drop_Off_Date = drop_Off_Date;
    }

    public String getPick_Up_Time() {
        return pick_Up_Time;
    }

    public void setPick_Up_Time(String pick_Up_Time) {
        this.pick_Up_Time = pick_Up_Time;
    }

    public String getDrop_Off_Time() {
        return drop_Off_Time;
    }

    public void setDrop_Off_Time(String drop_Off_Time) {
        this.drop_Off_Time = drop_Off_Time;
    }

    public String getCar_Name() {
        return car_Name;
    }

    public void setCar_Name(String car_Name) {
        this.car_Name = car_Name;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getDriver_Age() {
        return driver_Age;
    }

    public void setDriver_Age(String driver_Age) {
        this.driver_Age = driver_Age;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
