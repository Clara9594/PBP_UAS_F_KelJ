package com.example.tugasbesar_pbp_f;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("profile")
    Call<UserResponse> getAllUser(@Query("data") String data);

    @GET("profile/{id}")
    Call<UserResponse> getUserById(@Path("id")String id,
                                   @Query("data") String data);
    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> login(@Field("email") String email,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<UserResponse> register(@Field("name") String name,
                                @Field("phone") String phone,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("country") String country);

    //Berbagai method buat BOOKING/////////////////////////////////////////
    @GET("booking")
    Call<BookingResponse> getAllBooking(@Query("data") String data);

    @GET("booking/{id}")
    Call<BookingResponse> getBookingById(@Path("id") String id,
                                         @Query("data") String data);

    @GET("bookingProcess/{id}")
    Call<BookingResponse> getBookingProcessByIdPelanggan(@Path("id") String id,
                                         @Query("data") String data);

    @POST("booking")
    @FormUrlEncoded
    Call<BookingResponse> createBooking(@Field("id_Pelanggan") int id_Pelanggan,
                                        @Field("name" ) String name,
                                        @Field("pick_Up_Location") String pick_Up_Location,
                                        @Field("pick_Up_Date") String pick_Up_Date,
                                        @Field("drop_Off_Date") String drop_Off_Date,
                                        @Field("pick_Up_Time") String pick_Up_Time,
                                        @Field("drop_Off_Time") String drop_Off_Time,
                                        @Field("car_Name") String car_Name,
                                        @Field("plat_nomor") String plat_nomor,
                                        @Field("driver_Age") String driver_Age,
                                        @Field("total") int total,
                                        @Field("status") String status);

    @PUT("booking/{id}")
    @FormUrlEncoded
    Call<BookingResponse> updateBooking(@Path("id") String id,
                                        //@Field("pick_Up_Location") String pick_Up_Location,
                                        @Field("pick_Up_Date") String pick_Up_Date,
                                        @Field("drop_Off_Date") String drop_Off_Date,
                                        @Field("pick_Up_Time") String pick_Up_Time,
                                        @Field("drop_Off_Time") String drop_Off_Time,
                                       // @Field("car_Name") String car_Name,
                                       // @Field("plat_nomor") String plat_nomor,
                                        @Field("driver_Age") String driver_Age,
                                        @Field("total") int total);

    @DELETE("booking/{id}")
    Call<BookingResponse> deleteBooking(@Path("id") String id);

    //////BERBAGAI METHOD CAR
//    @Multipart
//    @POST("car")
//    Call<CarResponse> createCar(@Part("tipe") RequestBody tipe,
//                                @Part("merek") RequestBody merek,
//                                @Part("penumpang") RequestBody penumpang,
//                                @Part("tas") RequestBody tas,
//                                @Part("bensin")RequestBody bensin,
//                                @Part("harga")RequestBody harga,
//                                @Part MultipartBody.Part imgURL,
//                                @Part("plat_nomor")RequestBody plat_nomor);

    @POST("car")
    @FormUrlEncoded
    Call<CarResponse> createCar(@Field("tipe") String tipe,
                                @Field("merek") String merek,
                                @Field("penumpang") int penumpang,
                                @Field("tas") int tas,
                                @Field("bensin") String bensin,
                                @Field("harga") int harga,
                                @Field("imgURL") String imgURL,
                                @Field("plat_nomor") String plat_nomor);

    @PUT("car/{id}")
    @FormUrlEncoded
    Call<CarResponse> updateCar(@Path("id") int id,
                                @Field("tipe") String tipe,
                                @Field("merek" ) String merek,
                                @Field("penumpang") int penumpang,
                                @Field("tas") int tas,
                                @Field("bensin") String bensin,
                                @Field("harga") int harga,
                                @Field("imgURL") String imgURL,
                                @Field("plat_nomor") String plat_nomor);


    @GET("car")
    Call<CarResponse> getAllCars(@Query("data") String data);

    @GET("car/{id}")
    Call<CarResponse> getCarById(@Path("id") String id,
                                         @Query("data") String data);

    @GET("carByPlat/{plat_nomor}")
    Call<CarResponse> getCarByPlat(@Path("plat_nomor") String plat_nomor,
                                   @Query("data") String data);

    @DELETE("car/{id}")
    Call<CarResponse> deleteCar(@Path("id") String id);


}

