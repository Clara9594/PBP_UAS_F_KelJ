package com.example.tugasbesar_pbp_f;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasbesar_pbp_f.databinding.ActivityPickcarBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PickCar extends AppCompatActivity {
    private ActivityPickcarBinding activityPickcarBinding;
    private ArrayList<Car> ListCar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton back;
    public Bundle mBundle;
    public long temp1, temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListCar = new CarList().CAR;
        adapter = new RecyclerViewAdapter(PickCar.this, ListCar);
//        mBundle = getIntent().getBundleExtra("durasi");
//        temp1 = mBundle.getLong("hari");
//        temp2 = mBundle.getLong("jam");

//        back = findViewById(R.id.bckDate);
//        back.setOnClickListener(view -> {
//            Intent back = new Intent(PickCar.this, DateActivity.class);
//            startActivity(back);
//        });

        activityPickcarBinding = DataBindingUtil.setContentView(this,R.layout.activity_pickcar);
        activityPickcarBinding.recyclerViewCar.setLayoutManager(new LinearLayoutManager(this));
        activityPickcarBinding.recyclerViewCar.setItemAnimator(new DefaultItemAnimator());
        activityPickcarBinding.recyclerViewCar.setAdapter(adapter);
    }
}
