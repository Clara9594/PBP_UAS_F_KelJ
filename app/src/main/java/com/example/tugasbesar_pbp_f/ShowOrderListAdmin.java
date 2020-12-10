package com.example.tugasbesar_pbp_f;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowOrderListAdmin extends AppCompatActivity {
    private ImageButton btn;
    private RecyclerView recyclerView;
    private ShowOrderListAdapter recyclerAdapter;
    private List<BookingDAO> booking = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;
    private int id_Pelanggan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_list_admin);

//        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
//        id_Pelanggan = Integer.parseInt(sharedPreferences.getString("id",null));

        btn = findViewById(R.id.bckDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);
        loadBooking();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBooking();
            }
        });
    }

    public void loadBooking(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookingResponse> call = apiService.getAllBooking("data");
        //Call<BookingResponse> call = apiService.getAllBooking("data");

        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if(response.body()==null){
                    Toast.makeText(ShowOrderListAdmin.this,"Order List Kosong",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    generateDataList(response.body().getBookings());
                    Toast.makeText(ShowOrderListAdmin.this, "Success", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(ShowOrderListAdmin.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<BookingDAO> bookingList){
        recyclerView = findViewById(R.id.recycler_view_bookingInAdmin);
        recyclerAdapter = new ShowOrderListAdapter(this, bookingList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( ShowOrderListAdmin.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }
}
