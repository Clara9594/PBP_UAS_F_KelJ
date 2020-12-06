package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private UserDAO users = null;

    @SerializedName("message")
    @Expose
    private String message;

//    @SerializedName("status")
//    @Expose
//    private String status;

    public UserDAO getUsers(){
        return users;
    }

    public String getMessage(){
        return message;
    }

    public void setUsers (UserDAO users){
        this.users = users;
    }

    public void setMessage (String message){
        this.message = message;
    }

//    public void getStatus(String status) {
//        this.status = status;
//    }
}
