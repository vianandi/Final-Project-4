package com.mbkm.project4.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbkm.project4.R;
import com.mbkm.project4.Model.HistoryModel;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<HistoryModel> {

    public HistoryAdapter(Activity context, ArrayList<HistoryModel> notification) {
        super(context, 0, notification);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_booking, parent, false);
        }

        HistoryModel current = getItem(position);

        TextView idBook = listItemView.findViewById(R.id.id_booking);
        idBook.setText("ID : " + current.getmIdBook());

        TextView tanggal = listItemView.findViewById(R.id.tanggal);
        tanggal.setText(current.getmTanggal());

        TextView riwayat = listItemView.findViewById(R.id.riwayat);
        riwayat.setText(current.getmRiwayat());

        TextView tvBus = listItemView.findViewById(R.id.tv_bus);
        tvBus.setText("Bus :");

        TextView bus = listItemView.findViewById(R.id.bus);
        bus.setText(current.getmBus());

        TextView tvTotal = listItemView.findViewById(R.id.tv_total);
        tvTotal.setText("Total :");

        TextView total = listItemView.findViewById(R.id.total);
        total.setText("Rp. " + current.getmTotal());


        ImageView imageIcon = listItemView.findViewById(R.id.image);

        if (current.hasImage()) {
            imageIcon.setImageResource(current.getmImageResourceId());
            imageIcon.setVisibility(View.VISIBLE);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}