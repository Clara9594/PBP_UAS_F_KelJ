package com.example.tugasbesar_pbp_f;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MyBookingRecyclerAdapter extends RecyclerView.Adapter<MyBookingRecyclerAdapter.RoomViewHolder> implements Filterable {

    private List<BookingDAO> dataList,filteredDataList;
    private Context context;

    public MyBookingRecyclerAdapter(Context context, List<BookingDAO> dataList){
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recycler_view_my_booking, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingRecyclerAdapter.RoomViewHolder holder, int position) {
        final BookingDAO brg = filteredDataList.get(position);
        holder.twBookingDate1.setText(brg.getPick_Up_Date());
        holder.twBookingDate2.setText(brg.getDrop_Off_Date());
        holder.twPickUpTime.setText(brg.getPick_Up_Time());
        holder.twDropOffTime.setText(brg.getDrop_Off_Time());
        holder.twCarName.setText(brg.getCar_Name());
        holder.twTotalPrice.setText(String.valueOf(brg.getTotal()));

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailBooking dialog = new DetailBooking();
                dialog.show(manager,"dialog");

                Bundle args = new Bundle();
                args.putInt("id",brg.getId());
                dialog.setArguments(args);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twBookingDate1,twBookingDate2,twPickUpTime,twDropOffTime;
        private TextView twCarName,twTotalPrice;
        private MaterialButton btnDetail;
        private ConstraintLayout mParent;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twBookingDate1 = itemView.findViewById(R.id.twBookingDate1);
            twBookingDate2 = itemView.findViewById(R.id.twBookingDate2);
            twPickUpTime    = itemView.findViewById(R.id.twPickupTime);
            twDropOffTime = itemView.findViewById(R.id.twDropOffTime);
            twCarName = itemView.findViewById(R.id.twCarName);
            twTotalPrice = itemView.findViewById(R.id.twTotalPrice);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            mParent = itemView.findViewById(R.id.constlayout);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint){
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()){
                    filteredDataList = dataList;
                }else{
                    List<BookingDAO> filteredList = new ArrayList<>();
                    for(BookingDAO bookingDAO : dataList){
                        if(bookingDAO.getCar_Name().toLowerCase().contains(charSequenceString.toLowerCase())){
                            filteredList.add(bookingDAO);
                        }
                        filteredDataList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results){
                filteredDataList = (List<BookingDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
