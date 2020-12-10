package com.example.tugasbesar_pbp_f;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBookingInAdmin extends DialogFragment {
    private TextView twId, twName, twPickUpLocation, twBookingDate, twBookingTime, twCarName,twDriverAge,
            twTotal,twCarPlat;
    private String sName, sPickUpLocation, sPickUpDate, sDropOffDate, sPickUpTime, sDropOffTime
            ,sCarName, sDriverAge, sCarPlat ;
    private int sIdBooking, sTotal;
    private ImageButton ibClose;

    public static DetailBookingInAdmin newInstance(){return new DetailBookingInAdmin();}

    @Override
    public void onStart(){
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_detail_booking_in_admin,container,false);
        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twId = v.findViewById(R.id.twId);
        twName = v.findViewById(R.id.twName);
        twPickUpLocation = v.findViewById(R.id.twPickUp);
        twBookingDate = v.findViewById(R.id.twDate);
        twBookingTime = v.findViewById(R.id.twTime);
        twCarName = v.findViewById(R.id.twCarName);
        twCarPlat = v.findViewById(R.id.twCarPlat);
        twDriverAge = v.findViewById(R.id.twDriverAge);
        twTotal = v.findViewById(R.id.twPrice);

        sIdBooking = getArguments().getInt("id",-1);
        loadUserById(sIdBooking);
        return v;
    }

    private void loadUserById(int id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> add = apiService.getBookingById(String.valueOf(id),"data");

        add.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                String BookingDate, BookingTime;

                sName = response.body().getBookings().get(0).getName();
                sPickUpLocation =  response.body().getBookings().get(0).getPick_Up_Location();
                sPickUpDate =   response.body().getBookings().get(0).getPick_Up_Date();
                sDropOffDate =  response.body().getBookings().get(0).getDrop_Off_Date();
                sPickUpTime =  response.body().getBookings().get(0).getPick_Up_Time();
                sDropOffTime = response.body().getBookings().get(0).getDrop_Off_Time();
                sCarName =  response.body().getBookings().get(0).getCar_Name();
                sCarPlat = response.body().getBookings().get(0).getPlat_nomor();
                sDriverAge = response.body().getBookings().get(0).getDriver_Age();
                sTotal = response.body().getBookings().get(0).getTotal();

                BookingDate = sPickUpDate + " - " + sDropOffDate;
                BookingTime = sPickUpTime + " - " + sDropOffTime;

                twId.setText(String.valueOf(sIdBooking));
                twName.setText(sName);
                twPickUpLocation.setText(sPickUpLocation);
                twBookingDate.setText(BookingDate);
                twBookingTime.setText(BookingTime);
                twCarName.setText(sCarName);
                twCarPlat.setText(sCarPlat);
                twDriverAge.setText(sDriverAge);
                twTotal.setText(String.valueOf(sTotal));
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }
}
