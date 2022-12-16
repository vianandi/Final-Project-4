package com.mbkm.project4.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mbkm.project4.Model.BusModel;
import com.mbkm.project4.Model.PlacesData;
import com.mbkm.project4.R;
import com.mbkm.project4.bus_detail;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusDataViewHolder> {

    Context context;
    List<BusModel> busModelList;

    public BusAdapter(Context context, List<BusModel> busModelList) {
        this.context = context;
        this.busModelList = busModelList;
    }

    @NonNull
    @Override
    public BusAdapter.BusDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_bus, parent, false);

        // here we create a recyclerview list item layout file
        return new BusAdapter.BusDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter.BusDataViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.busName.setText(busModelList.get(position).getBusName());
        holder.type.setText(busModelList.get(position).getType());
        holder.desk.setText(busModelList.get(position).getDesk());
        holder.price.setText(busModelList.get(position).getPrice());
        holder.busImage.setImageResource(busModelList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "anda memilih " + busModelList.get(position).getBusName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, bus_detail.class);

                intent.putExtra("busName", busModelList.get(position).getBusName());
                intent.putExtra("type", busModelList.get(position).getType());
                intent.putExtra("desk", busModelList.get(position).getDesk());
                intent.putExtra("price", busModelList.get(position).getPrice());
                intent.putExtra("busImage", busModelList.get(position).getImageUrl());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return busModelList.size();
    }

    public class BusDataViewHolder extends RecyclerView.ViewHolder {
        ImageView busImage;
        TextView busName, type, price, desk;

        public BusDataViewHolder(@NonNull View itemView) {
            super(itemView);

            busImage = itemView.findViewById(R.id.image);
            busName = itemView.findViewById(R.id.bus_name);
            type = itemView.findViewById(R.id.type);
            desk = itemView.findViewById(R.id.seat);
            price = itemView.findViewById(R.id.price);
        }
    }
}