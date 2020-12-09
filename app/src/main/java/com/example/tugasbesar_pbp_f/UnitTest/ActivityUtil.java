package com.example.tugasbesar_pbp_f.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.example.tugasbesar_pbp_f.AdminActivity;
import com.example.tugasbesar_pbp_f.MainActivity;
import com.example.tugasbesar_pbp_f.UserDAO;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startAdminActivity() {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void startMainActivity(UserDAO user) {
        Intent i = new Intent(context, AdminActivity.class);
        i.putExtra("id",user.getId());
        i.putExtra("name",user.getName());
        i.putExtra("phone",user.getPhone());
        i.putExtra("email",user.getEmail());
        i.putExtra("password",user.getPassword());
        i.putExtra("country",user.getCountry());
        i.putExtra("status",user.getStatus());
        i.putExtra("url",user.getUrl());
        context.startActivity(i);
    }
}
