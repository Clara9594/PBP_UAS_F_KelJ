package com.example.tugasbesar_pbp_f;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingResponse {
    @SerializedName("data")
    @Expose
    private List<BookingDAO> bookings = null;

    @SerializedName("message")
    @Expose
    private  String message;

    public List<BookingDAO> getBookings(){
        return bookings;
    }

    public String getMessage(){
        return message;
    }

    public void setBookings(List<BookingDAO> bookings) {
        this.bookings = bookings;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
