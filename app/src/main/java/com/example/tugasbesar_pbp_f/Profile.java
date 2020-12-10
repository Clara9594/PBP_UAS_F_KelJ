package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    private ImageButton ibBack;
    private MaterialButton btnSave,btnUpload;
    private ImageView ivProfile;
    private EditText etName, etPhone, etCountry;
    private String sName, sCountry, sPhone, sIMG;
    private int idUser;
    private Bitmap bitmap;
    private final int MY_PERMISSION_REQUEST = 777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        idUser = Integer.parseInt(sharedPreferences.getString("id",null));
        btnUpload = findViewById(R.id.btnUpload);
        loadUserById(idUser);

        ibBack = findViewById(R.id.btnBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        etName = findViewById(R.id.inName);
        etPhone = findViewById(R.id.inPhone);
        etCountry = findViewById(R.id.inCountry);
        ivProfile = findViewById(R.id.profile);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
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

    private void updateProfile(){
        String profileImage = imageToString();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> update = apiService.updateProfile(String.valueOf(idUser),
                etName.getText().toString(), etPhone.getText().toString(), etCountry.getText().toString(),profileImage);
        update.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(Profile.this, response.body().getMessage() , Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserById(int id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserById(String.valueOf(id), "data");

        add.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                sName = response.body().getUsers().getName();
                sPhone = response.body().getUsers().getPhone();
                sCountry = response.body().getUsers().getCountry();
                sIMG = response.body().getUsers().getUrl();

                etName.setText(sName);
                etPhone.setText(sPhone);
                etCountry.setText(sCountry);
                Glide.with(Profile.this)
                        .load("https://cardido.masuk.web.id/storage/app/public/" + sIMG)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(ivProfile);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                ivProfile.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

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