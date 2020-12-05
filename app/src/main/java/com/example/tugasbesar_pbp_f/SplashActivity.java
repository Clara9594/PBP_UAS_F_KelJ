package com.example.tugasbesar_pbp_f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.tugasbesar_pbp_f.Login;
import com.example.tugasbesar_pbp_f.MainActivity;
import com.example.tugasbesar_pbp_f.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Login.statusLogin = getSharedPreferences("status",0);
//        Boolean booleanValue = Login.statusLogin.getBoolean("status_login",true);

//        if(booleanValue)
//        {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//        }

//        else {
            setContentView(R.layout.activity_splash);
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(new Intent(SplashActivity.this, Login.class));
                    }
                }
            };
            thread.start();
        }
    }
//}