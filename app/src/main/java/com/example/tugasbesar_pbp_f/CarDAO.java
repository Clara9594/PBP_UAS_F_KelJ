package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.SerializedName;

public class CarDAO {
    @SerializedName("id")
    public int id;

    @SerializedName("tipe")
    public String tipe;

    @SerializedName("merek")
    public String merek;

    @SerializedName("penumpang")
    public int penumpang;

    @SerializedName("tas")
    public int tas;

    @SerializedName("bensin")
    public String bensin;

    @SerializedName("harga")
    public int harga;

    @SerializedName("imgURL")
    public String imgURL;

    @SerializedName("plat_nomor")
    public String plat_nomor;

    public CarDAO(int id, String tipe, String merek, int penumpang, int tas, String bensin, int harga, String imgURL, String plat_nomor) {
        this.id = id;
        this.tipe = tipe;
        this.merek = merek;
        this.penumpang = penumpang;
        this.tas = tas;
        this.bensin = bensin;
        this.harga = harga;
        this.imgURL = imgURL;
        this.plat_nomor = plat_nomor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public int getPenumpang() {
        return penumpang;
    }

    public void setPenumpang(int penumpang) {
        this.penumpang = penumpang;
    }

    public int getTas() {
        return tas;
    }

    public void setTas(int tas) {
        this.tas = tas;
    }

    public String getBensin() {
        return bensin;
    }

    public void setBensin(String bensin) {
        this.bensin = bensin;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }
}
