package com.example.tugasbesar_pbp_f;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tugasbesar_pbp_f.databinding.ActivityPickcarBinding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickCar extends AppCompatActivity {
    private List<CarDAO> cars = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton back;
    public Bundle mBundle;
    public long temp1, temp2;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickcar);
        //adapter = new RecyclerViewAdapter(PickCar.this, ListCar);
//        mBundle = getIntent().getBundleExtra("durasi");
//        temp1 = mBundle.getLong("hari");
//        temp2 = mBundle.getLong("jam");

//        back = findViewById(R.id.bckDate);
//        back.setOnClickListener(view -> {
//            Intent back = new Intent(PickCar.this, DateActivity.class);
//            startActivity(back);
//        });
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);
        loadCar();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCar();
            }
        });

    }

    public void loadCar(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarResponse> call = apiService.getAllCars("data");

        call.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                if(response.body()==null){
                    Toast.makeText(PickCar.this,"List Mobil kosong",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    generateDataList(response.body().getCars());
                    //Toast.makeText(PickCar.this, "BISA CUK", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                Toast.makeText(PickCar.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<CarDAO> carList){
        recyclerView = findViewById(R.id.recycler_view_car);
        adapter = new RecyclerViewAdapter(this, carList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( PickCar.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                recyclerAdapter.getFilter().filter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                recyclerAdapter.getFilter().filter(s);
//                return false;
//            }
//        });
    }
}
