package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {
    private CardView cvAddUser, cvCarList;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userLogin";
    private static final String KEY_ID = "id";
    private String sIdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        cvAddUser = findViewById(R.id.cvAddUser);
        cvCarList = findViewById(R.id.cvCarList);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        sIdUser = sharedPreferences.getString(KEY_ID, null);

        cvAddUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                Intent i = new Intent(AdminActivity.this, AddCar.class);
                startActivity(i);
            }
        });

        cvCarList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this,ShowListCarAdmin.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(AdminActivity.this, Login.class);
        startActivity(intent);
        finish();
    }
}