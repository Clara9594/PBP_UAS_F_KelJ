package com.example.tugasbesar_pbp_f;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Profile extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private ImageView photo;
    private ImageButton back;
    private TextInputLayout usernameLayout;
    private TextInputLayout emailLayout;
    private TextInputEditText username;
    private TextInputEditText phone;
    private SharedPreferences preferences;
    private MaterialButton btn;
    public static final int mode = Activity.MODE_PRIVATE;
    private String Username = "";
    private String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadPreferences();
        setProfile();
        drawerLayout = findViewById(R.id.drawer_layout);
        usernameLayout = findViewById(R.id.mail);
        emailLayout = findViewById(R.id.pass);
        btn = findViewById(R.id.btnSave);
        photo = findViewById(R.id.profile);
        back = findViewById(R.id.back);

        //Request For Camera Permission
        if(ContextCompat.checkSelfPermission(Profile.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Profile.this,new String[]{
                    Manifest.permission.CAMERA
            },100);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Open Camera
                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photo,100);
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent back = new Intent(Profile.this, MainActivity.class);
                startActivity(back);
            }
        });
    }

    private void loadPreferences() {
        String name = "profile";
        preferences = getSharedPreferences(name, mode);
        if (preferences!=null){
            Username = preferences.getString("Username", "");
            phoneNumber = preferences.getString("phoneNumber", "");
        }
    }

    private void setProfile() {
        username = findViewById(R.id.inputUsername);
        phone= findViewById(R.id.phoneInput);
        username.setText(Username);
        phone.setText(phoneNumber);
    }

    private void saveData() {
        username = findViewById(R.id.inputUsername);
        phone= findViewById(R.id.phoneInput);
        photo = findViewById(R.id.profile);
        SharedPreferences.Editor editor = preferences.edit();
        if (!username.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()){
            editor.putString("Username", username.getText().toString());
            editor.putString("phoneNumber", phone.getText().toString());
            editor.apply();
            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Fill correctly", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //Get Capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //Set Capture Image to ImageView
            photo.setImageBitmap(captureImage);
        }
    }

    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickProfil(View view){
        recreate();
    }

    public void ClickSettings(View view){
        MainActivity.redirectActivity(this,SettingsMode.class);
    }

    public void ClickAbout(View view){
        MainActivity.redirectActivity(this, About.class);
    }

//    public void ClickSignOut(View view){
//        MainActivity.logout(this);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }
}