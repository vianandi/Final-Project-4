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
import androidx.recyclerview.widget.RecyclerView;

import com.mbkm.project4.R;
import com.mbkm.project4.Model.PlacesData;
import com.mbkm.project4.details_activity;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.TopPlacesViewHolder> {

    Context context;
    List<PlacesData> topPlacesDataList;

    public PlacesAdapter(Context context, List<PlacesData> topPlacesDataList) {
        this.context = context;
        this.topPlacesDataList = topPlacesDataList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.places_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.countryName.setText(topPlacesDataList.get(position).getCountryName());
        holder.placeName.setText(topPlacesDataList.get(position).getPlaceName());
        holder.price.setText(topPlacesDataList.get(position).getPrice());
        holder.placeImage.setImageResource(topPlacesDataList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "anda memilih " + topPlacesDataList.get(position).getPlaceName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, details_activity.class);

                intent.putExtra("placeName", topPlacesDataList.get(position).getPlaceName());
                intent.putExtra("countryName", topPlacesDataList.get(position).getCountryName());
                intent.putExtra("price", topPlacesDataList.get(position).getPrice());
                intent.putExtra("placeImage", topPlacesDataList.get(position).getImageUrl());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return topPlacesDataList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, countryName, price;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);

        }
    }
}