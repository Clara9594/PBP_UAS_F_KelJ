package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCar extends AppCompatActivity {
    private EditText inCarName,inCarType, inPassanger,inBags,inFuel,inTotal,inCarPlat;
    private MaterialButton btnCreate, btnUnggah;
    private String filePath="";
    private Bitmap bitmap;
    private ImageView imageView;
    private final int MY_PERMISSION_REQUEST = 777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        if(ContextCompat.checkSelfPermission(AddCar.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddCar.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST);
        }

        inCarName = findViewById(R.id.inCarName);
        inCarType = findViewById(R.id.inCarType);
        inPassanger = findViewById(R.id.inPassanger);
        inBags = findViewById(R.id.inBags);
        inFuel = findViewById(R.id.inFuel);
        inTotal = findViewById(R.id.inTotal);
        inCarPlat = findViewById(R.id.inCarPlat);
        btnCreate = findViewById(R.id.btnSaveCar);
        btnUnggah = findViewById(R.id.btnGaleri);
        imageView = findViewById(R.id.imageView4);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inCarName.getText().toString().isEmpty() && inCarType.getText().toString().isEmpty() && inPassanger.getText().toString().isEmpty()
                && inBags.getText().toString().isEmpty() && inFuel.getText().toString().isEmpty() && inTotal.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Car's Data", Toast.LENGTH_SHORT).show();
                }else if(inCarName.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Car's Name", Toast.LENGTH_SHORT).show();
                }else if(inCarType.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Car's Type", Toast.LENGTH_SHORT).show();
                }else if(inPassanger.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Passanger", Toast.LENGTH_SHORT).show();
                }else if(inBags.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Bags", Toast.LENGTH_SHORT).show();
                }else if(inFuel.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Fuel", Toast.LENGTH_SHORT).show();
                }else if(inTotal.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please Enter Total", Toast.LENGTH_SHORT).show();
                }
                saveCar();
            }
        });

        btnUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent();
               i.setType("image/*");
               i.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(i,MY_PERMISSION_REQUEST);
            }
        });
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

    private void saveCar(){
        String CarImage = imageToString();
        if(CarImage==null){
            Toast.makeText(AddCar.this,"Doesn't Upload Photo", Toast.LENGTH_SHORT).show();
        }else {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CarResponse> add = apiService.createCar(inCarName.getText().toString(),
                    inCarType.getText().toString(), Integer.parseInt(inPassanger.getText().toString()),
                    Integer.parseInt(inBags.getText().toString()), inFuel.getText().toString(),
                    Integer.parseInt(inTotal.getText().toString()), CarImage, inCarPlat.getText().toString());
            add.enqueue(new Callback<CarResponse>() {
                @Override
                public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                    Toast.makeText(AddCar.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<CarResponse> call, Throwable t) {
                    Toast.makeText(AddCar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();
                }
            });
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if(bitmap!=null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageInByte,Base64.DEFAULT);

        }
        return null;
    }

}