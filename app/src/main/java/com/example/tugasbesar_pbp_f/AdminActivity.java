package com.example.tugasbesar_pbp_f;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class AdminActivity extends AppCompatActivity {
    private CardView cvAddUser, cvCarList,cvOrderList;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userLogin";
    private static final String KEY_ID = "id";
    private String sIdUser;
    private MaterialButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        cvAddUser = findViewById(R.id.cvAddUser);
        cvCarList = findViewById(R.id.cvCarList);
        cvOrderList = findViewById(R.id.cvOrderList);
        btnLogout = findViewById(R.id.btnLogout);

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

        cvOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, ShowOrderListAdmin.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure want to logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
//                SharedPreferences.Editor editor =  Login.statusLogin.edit();
//                editor.putBoolean("status_login",false);
//                editor.commit();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", null);
                        editor.putString("status",null);
                        editor.commit();

                        AdminActivity.this.finishAffinity();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();
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