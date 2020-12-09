package com.example.tugasbesar_pbp_f;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailCar extends DialogFragment {

    private ImageView image_car;
    private ImageButton ibClose;
    private TextView twIDCar,twCarName,twCarType,twPassengers,twBags,twFuel,twPrice,twPlat;
    private MaterialButton btnDelete, btnEdit;
    private int sIdCar,sPrice,sPassengers,sBags;
    private String sCarName,sCarType,sFuel,sImageCar,sCarPlat;

    public static DetailCar newInstance(){return new DetailCar();}

    @Override
    public void onStart(){
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_detail_car,container,false);
        sIdCar = getArguments().getInt("idCar",-1);
        loadCarById(sIdCar);
        btnEdit = v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), EditCarAdmin.class);
                Bundle dataEdit = new Bundle();
                dataEdit.putInt("ID",sIdCar);
                i.putExtra("dataEdit", dataEdit);
                startActivity(i);
                dismiss();
            }
        });

        btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Konfirmasi");
                builder.setMessage("Yakin ingin menghapus data ini?");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        requestDelete(sIdCar);
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

        twIDCar = v.findViewById(R.id.twIDCar);
        twCarName   = v.findViewById(R.id.twCarName);
        twCarType   = v.findViewById(R.id.twCarType);
        twPlat = v.findViewById(R.id.twCarPlat);
        twPassengers    = v.findViewById(R.id.twPassengers);
        twBags      =v.findViewById(R.id.twBags);
        twFuel      = v.findViewById(R.id.twFuel);
        twPrice     =v.findViewById(R.id.twPrice);
        image_car = v.findViewById(R.id.image_car);
        btnDelete = v.findViewById(R.id.btnDelete);
        btnEdit = v.findViewById(R.id.btnEdit);
        return v;
    }

    private void loadCarById(int id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> add = apiService.getCarById(String.valueOf(id),"data");

        add.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                sCarName =  response.body().getCars().get(0).getMerek();
                sCarType = response.body().getCars().get(0).getTipe();
                sPassengers = response.body().getCars().get(0).getPenumpang();
                sBags =  response.body().getCars().get(0).getTas();
                sFuel =   response.body().getCars().get(0).getBensin();
                sPrice = response.body().getCars().get(0).getHarga();
                sImageCar = response.body().getCars().get(0).getImgURL();
                sCarPlat = response.body().getCars().get(0).getPlat_nomor();


                twIDCar.setText(String.valueOf(sIdCar));
                twCarName.setText(sCarName);
                twCarType.setText(sCarType);
                twPassengers.setText(String.valueOf(sPassengers));
                twBags.setText(String.valueOf(sBags));
                twFuel.setText(sFuel);
                twPrice.setText(String.valueOf(sPrice));
                twPlat.setText(sCarPlat);
                Glide.with(getContext())
                        .load("https://cardido.masuk.web.id/storage/app/public/" + sImageCar)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(image_car);
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

    private void requestDelete(int id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> del = apiInterface.deleteCar(String.valueOf(id));
        del.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                if(response.isSuccessful()){
                    dismiss();
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(getActivity(),ShowListCarAdmin.class);
                    startActivity(i);
                }else{
                    dismiss();
                    //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}