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
import com.mbkm.project4.Model.RecentData;
import com.mbkm.project4.details_activity;



import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentsViewHolder> {

    Context context;
    List<RecentData> recentsDataList;

    public RecentAdapter(Context context, List<RecentData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.countryName.setText(recentsDataList.get(position).getCountryName());
        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
        holder.price.setText(recentsDataList.get(position).getPrice());
        holder.placeImage.setImageResource(recentsDataList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "anda memilih " + recentsDataList.get(position).getPlaceName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, details_activity.class);

                intent.putExtra("placeName", recentsDataList.get(position).getPlaceName());
                intent.putExtra("countryName", recentsDataList.get(position).getCountryName());
                intent.putExtra("price", recentsDataList.get(position).getPrice());
                intent.putExtra("placeImage", recentsDataList.get(position).getImageUrl());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return recentsDataList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, countryName, price;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);

        }
    }
}