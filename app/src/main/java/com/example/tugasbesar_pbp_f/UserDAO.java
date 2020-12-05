package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.SerializedName;

public class UserDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("country")
    private String country;

    @SerializedName("status")
    private String status;

    @SerializedName("url")
    private String url;

    public UserDAO(String id, String name, String phone, String email, String password, String country, String status, String url){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.country = country;
        this.status = status;
        this.url = url;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getCountry(){
        return country;
    }

    public String getStatus(){
        return status;
    }

    public String getUrl(){
        return url;
    }



    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String Password){
        this.password = password;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setUrl(String url){
        this.url = url;
    }


}
