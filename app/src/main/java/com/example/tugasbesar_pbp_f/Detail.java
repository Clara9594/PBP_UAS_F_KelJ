package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tugasbesar_pbp_f.DateActivity.driver_Age;
import static com.example.tugasbesar_pbp_f.DateActivity.drop_Off_Date;
import static com.example.tugasbesar_pbp_f.DateActivity.drop_Off_Time;
import static com.example.tugasbesar_pbp_f.DateActivity.pick_Up_Location;
import static com.example.tugasbesar_pbp_f.DateActivity.pick_Up_Time;
import static com.example.tugasbesar_pbp_f.DateActivity.pick_Up_date;


public class Detail extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private String sNama;
    private MaterialTextView a1,a2,a3;
    public Bundle mBundle,mBundle1;
    private int harga, hari, jam;
    public long temp1, temp2;
    private MaterialButton btnPay;
    private ImageButton btnB;
    private String plat_nomor, car_Name;
    //,pick_Up_Location,pick_Up_Date,drop_Off_Date,pick_Up_Time;
    //private String drop_Off_Time,total,status;
    private int id_Pelanggan=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        id_Pelanggan = Integer.parseInt(sharedPreferences.getString("id",null));
        loadUserById(id_Pelanggan);

        a1 = findViewById(R.id.teks1);
        a2 = findViewById(R.id.teks2);
        a3 = findViewById(R.id.teks3);
        btnPay = findViewById(R.id.pay);
        btnB = findViewById(R.id.backCar);
        mBundle = getIntent().getBundleExtra("hargaDetail");
        harga = mBundle.getInt("harga");
        car_Name = mBundle.getString("car_Name");
        plat_nomor = mBundle.getString("plat_nomor");
        a1.setText("Rp. "+String.valueOf(harga));
        a3.setText(String.valueOf(DateActivity.elapsedDays)  + " days " + String.valueOf(DateActivity.elapsedHours) +" hours ");

        //pick_Up_Location = mBundle1.getString("alamat");

//        mBundle1 = getIntent().getBundleExtra("durasi1");
//        temp1 = mBundle1.getLong("hari");
//        temp2 = mBundle1.getLong("jam");
        hari = (int) DateActivity.elapsedDays;
        jam = (int) DateActivity.elapsedHours;

        if(hari==0 && jam!=0) {
            a2.setText("Rp. "+String.valueOf(harga));
        }
        else if(hari!=0 && jam!=0){
            harga = harga * (hari+1);
            a2.setText("Rp. "+String.valueOf(harga));
        }

        else if(hari!=0 && jam==0)
        {
            harga = harga * hari;
            a2.setText("Rp. "+String.valueOf(harga));
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBooking();
                Intent pay = new Intent(Detail.this, Check.class);
                startActivity(pay);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Detail.this, PickCar.class);
                startActivity(back);
            }
        });
    }

    private void saveBooking(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> add = apiService.createBooking(id_Pelanggan, sNama, pick_Up_Location,
                pick_Up_date, drop_Off_Date, pick_Up_Time, drop_Off_Time,car_Name,plat_nomor,driver_Age,harga,"Process" );
        add.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                Toast.makeText(Detail.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(Detail.this, t.getMessage(), Toast.LENGTH_LONG).show();
                //progressDialog.dismiss();
            }
        });
    }
    private void loadUserById(int id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserById(String.valueOf(id),"data");

        add.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                sNama = response.body().getUsers().getName();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Detail.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}