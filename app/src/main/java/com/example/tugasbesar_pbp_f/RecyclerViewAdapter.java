package com.example.tugasbesar_pbp_f;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<CarDAO> result;


    public RecyclerViewAdapter(Context context, List<CarDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        AdapterRecyclerViewBinding adapterRecyclerViewBinding = DataBindingUtil.inflate(
//                LayoutInflater.from(parent.getContext()),R.layout.adapter_recycler_view,parent,false);
//        MyViewHolder myViewHolder = new MyViewHolder(adapterRecyclerViewBinding);
//
//        return myViewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recycler_view, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CarDAO car = result.get(position);
        //holder.imageView.setImageURI(car.getImgURL());
        Glide.with(context)
                //.load("https://cardido.masuk.web.id/public/gambarMobil/" + car.getImgURL())
                .load("https://cardido.masuk.web.id/storage/app/public/" + car.getImgURL())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);
        holder.nameCar.setText(car.getTipe());
        holder.brandCar.setText(car.getMerek());
        holder.passenger.setText(String.valueOf(car.getPenumpang()));
        holder.bag.setText(String.valueOf(car.getTas()));
        holder.fuel.setText(car.getBensin());
        holder.platNomor.setText(car.getPlat_nomor());
        holder.price.setText(String.valueOf(car.getHarga()));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int itemPosition = getLayoutPosition();
                Intent next = new Intent(context,Detail.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("harga",car.getHarga());
                mBundle.putString("car_Name", car.getMerek());
                mBundle.putString("plat_nomor",car.getPlat_nomor());
                next.putExtra("hargaDetail",mBundle);
                context.startActivity(next);
            }
        });


    }
  
    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameCar,brandCar,passenger,bag,fuel,platNomor,price;
        private CardView parent;
        //AdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //adapterRecyclerViewBinding = itemView;
            imageView = itemView.findViewById(R.id.image_car);
            nameCar = itemView.findViewById(R.id.name_car);
            brandCar = itemView.findViewById(R.id.brand_car);
            passenger = itemView.findViewById(R.id.passenger);
            bag = itemView.findViewById(R.id.bag);
            fuel = itemView.findViewById(R.id.fuel);
            platNomor = itemView.findViewById(R.id.platNomor);
            price = itemView.findViewById(R.id.price);
            parent = itemView.findViewById(R.id.itemcard);


        }
    }
}