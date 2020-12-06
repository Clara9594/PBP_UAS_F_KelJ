package com.example.tugasbesar_pbp_f;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailBooking extends DialogFragment {
    private TextView twId, twName, twPickUpLocation, twBookingDate, twBookingTime, twCarName,twDriverAge,
            twTotal,twCarPlat;
    private String sName, sPickUpLocation, sPickUpDate, sDropOffDate, sPickUpTime, sDropOffTime
            ,sCarName, sDriverAge, sCarPlat ;
    private int sIdBooking, sTotal;
    private ImageButton ibClose;
    private MaterialButton btnEdit, btnDelete;

    public static DetailBooking newInstance(){return new DetailBooking();}

    @Override
    public void onStart(){
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_detail_booking,container,false);

        btnEdit = v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), EditPesanan.class);
                Bundle dataEdit = new Bundle();
                dataEdit.putInt("ID",sIdBooking);
                i.putExtra("dataEdit", dataEdit);
                startActivity(i);
                dismiss();
            }
        });

        btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sIdBooking = getArguments().getInt("id",-1);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Konfirmasi");
                builder.setMessage("Yakin ingin menghapus data ini?");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        requestDelete(sIdBooking);
                    }
                });

                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

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

    private void requestDelete(int id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> del = apiInterface.deleteBooking(String.valueOf(id));
        del.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {

                if(response.isSuccessful()){
                    dismiss();
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(getActivity(),MyBookingActivity.class);
                    startActivity(i);
                }else{
                    dismiss();
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}