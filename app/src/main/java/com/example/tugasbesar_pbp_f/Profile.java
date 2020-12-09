package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    private ImageButton ibBack;
    private MaterialButton btnSave;
    private ImageView ivProfile;
    private EditText etName, etPhone, etCountry;
    private String sName, sCountry, sPhone;
    private  int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        idUser = Integer.parseInt(sharedPreferences.getString("id",null));
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

    }

    private void updateProfile(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> update = apiService.updateProfile(String.valueOf(idUser), etName.getText().toString(), etPhone.getText().toString(), etCountry.getText().toString());
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

                etName.setText(sName);
                etPhone.setText(sPhone);
                etCountry.setText(sCountry);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}