package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowListCarAdmin extends AppCompatActivity {
    private List<CarDAO> cars = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShowListCarAdminAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_car_admin);
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
                if(response.body()==null) {
                    Toast.makeText(ShowListCarAdmin.this, "List Mobil kosong", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    generateDataList(response.body().getCars());
                    Toast.makeText(ShowListCarAdmin.this, "Load Cars Data Successful", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                Toast.makeText(ShowListCarAdmin.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<CarDAO> carList){
        recyclerView = findViewById(R.id.recycler_view_car);
        adapter = new ShowListCarAdminAdapter(this, carList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( ShowListCarAdmin.this);
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