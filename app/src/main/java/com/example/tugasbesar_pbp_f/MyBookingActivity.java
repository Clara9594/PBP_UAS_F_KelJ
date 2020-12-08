package com.example.tugasbesar_pbp_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends AppCompatActivity {
    private ImageButton btn;
    private RecyclerView recyclerView;
    private MyBookingRecyclerAdapter recyclerAdapter;
    private List<BookingDAO> booking = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;
    private int id_Pelanggan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        id_Pelanggan = Integer.parseInt(sharedPreferences.getString("id",null));

        btn = findViewById(R.id.bckDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);
        loadBooking(id_Pelanggan);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBooking(id_Pelanggan);
            }
        });
    }

    public void loadBooking(int id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> call = apiService.getBookingProcessByIdPelanggan(String.valueOf(id),"data");
        //Call<BookingResponse> call = apiService.getAllBooking("data");

        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                generateDataList(response.body().getBookings());
                Toast.makeText(MyBookingActivity.this, "BISA CUK", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(MyBookingActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<BookingDAO> bookingList){
        recyclerView = findViewById(R.id.recycler_view_booking);
        recyclerAdapter = new MyBookingRecyclerAdapter(this, bookingList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( MyBookingActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

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