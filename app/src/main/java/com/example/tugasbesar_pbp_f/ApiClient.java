package com.example.tugasbesar_pbp_f;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //jgn lupa ganti BASE URL
    public static final String BASE_URL = "https://cardido.masuk.web.id/public/api/";
    public static Retrofit retrofit = null;
    private static Gson gson;

    public static Retrofit getClient(){
        if(retrofit == null){
//
//            gson = new GsonBuildConfig()
//                    .setLenient()
//                    .create();
//
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
