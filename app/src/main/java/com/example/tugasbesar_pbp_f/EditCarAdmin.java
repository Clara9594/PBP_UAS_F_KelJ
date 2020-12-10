package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Path;

public class EditCarAdmin extends AppCompatActivity {
    private TextInputEditText nama,tipe,plat,penumpang,tas,bensin,harga;
    private MaterialButton btnEdit, btnUpload;
    private String cNama;
    private String cTipe;
    private String cPlat;
    private int idCar;
    private int cPenumpang;
    private int cTas;
    private String cBensin;
    private String cIMG;
    private int cHarga;
    private ImageButton btnback;
    private final int MY_PERMISSION_REQUEST = 777;
    private String filePath="";
    private Bitmap bitmap;
    private ImageView imageView;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car_admin);

        if(ContextCompat.checkSelfPermission(EditCarAdmin.this,
            Manifest.permission.READ_EXTERNAL_STORAGE)!=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EditCarAdmin.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                MY_PERMISSION_REQUEST);
        }

        nama = findViewById(R.id.etNama);
        tipe = findViewById(R.id.etTipe);
        plat = findViewById(R.id.etPlat);
        penumpang = findViewById(R.id.etPss);
        tas = findViewById(R.id.etBag);
        bensin = findViewById(R.id.etFuel);
        harga = findViewById(R.id.edPrice);
        btnback = findViewById(R.id.ibBackAdmin);
        btnEdit = findViewById(R.id.btnEdit);
        btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imageView4);

        Bundle extras = getIntent().getBundleExtra("dataEdit");
        idCar = extras.getInt("ID");

        loadCarById(idCar);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdmin = new Intent (EditCarAdmin.this, AdminActivity.class);
                startActivity(toAdmin);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCar();

//                Toast.makeText(EditCarAdmin.this, idCar, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,MY_PERMISSION_REQUEST);
            }
        });
    }

    private void loadCarById(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> add = apiService.getCarById(String.valueOf(id),"data");

        add.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                cNama = response.body().getCars().get(0).getMerek();
                cTipe = response.body().getCars().get(0).getTipe();
                cPlat = response.body().getCars().get(0).getPlat_nomor();
                cPenumpang = response.body().getCars().get(0).getPenumpang();
                cTas = response.body().getCars().get(0).getTas();
                cBensin = response.body().getCars().get(0).getBensin();
                cHarga = response.body().getCars().get(0).getHarga();
                cIMG = response.body().getCars().get(0).getImgURL();

                nama.setText(cNama);
                tipe.setText(cTipe);
                plat.setText(cPlat);
                penumpang.setText(String.valueOf(cPenumpang));
                tas.setText(String.valueOf(cTas));
                bensin.setText(cBensin);
                harga.setText(String.valueOf(cHarga));
                Glide.with(EditCarAdmin.this)
                        .load("https://cardido.masuk.web.id/storage/app/public/" +cIMG)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(imageView);
            }

            @Override
            public void onFailure(retrofit2.Call<CarResponse> call, Throwable t) {
                Toast.makeText(EditCarAdmin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

    private void updateCar() {
        String CarImage = imageToString();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> update = apiService.updateCar(String.valueOf(idCar),tipe.getText().toString(),
                nama.getText().toString(),Integer.parseInt(penumpang.getText().toString()), Integer.parseInt(tas.getText().toString()),
                bensin.getText().toString(),Integer.parseInt(harga.getText().toString()),CarImage,plat.getText().toString());

        update.enqueue(new Callback<CarResponse>(){
            @Override
            public void onResponse(retrofit2.Call<CarResponse> call, Response<CarResponse> response) {
//                Toast.makeText(EditCarAdmin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent next = new Intent(EditCarAdmin.this, ShowListCarAdmin.class);
                startActivity(next);
               // progressDialog.dismiss();
            }

            @Override
            public void onFailure(retrofit2.Call<CarResponse> call, Throwable t) {
                Toast.makeText(EditCarAdmin.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
             //   progressDialog.dismiss();
            }
        });
    }

    private String imageToString() {
        if(bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageInByte,Base64.DEFAULT);
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_PERMISSION_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri path = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}