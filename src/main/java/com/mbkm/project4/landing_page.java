package com.mbkm.project4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mbkm.project4.adapter.AlertDialogManager;
import com.mbkm.project4.adapter.RecentAdapter;
import com.mbkm.project4.adapter.PlacesAdapter;
import com.mbkm.project4.Model.RecentData;
import com.mbkm.project4.Model.PlacesData;
import com.mbkm.project4.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class landing_page extends AppCompatActivity {

    ImageView logout, profile, orderlist, bus;
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentAdapter recentsAdapter;
    PlacesAdapter topPlacesAdapter;
    SessionManager session;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        ImageView imageView1 = (ImageView)findViewById(R.id.logout);
        ImageView imageView2 = (ImageView)findViewById(R.id.profile);
        ImageView imageView3 = (ImageView)findViewById(R.id.orderlist);
        ImageView imageView4 = (ImageView)findViewById(R.id.bus);
        ImageView imageView5 = (ImageView)findViewById(R.id.bookingtiket);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(landing_page.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(landing_page.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(landing_page.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(landing_page.this, ListBusActivity.class);
                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(landing_page.this, BookBusActivity.class);
                startActivity(intent);
            }
        });

        List<RecentData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentData("Dunia Fantasi, Ancol","Indonesia","Dimulai dari Rp 250.000",R.drawable.dufan));
        recentsDataList.add(new RecentData("Kawah Putih","Indonesia","Dimulai dari Rp 28.000",R.drawable.kawahputih));
        recentsDataList.add(new RecentData("Bromo","Indonesia","Dimulai dari Rp 50.000",R.drawable.bromo));
        recentsDataList.add(new RecentData("Coban Sewu","Indonesia","Dimulai dari Rp 10.000",R.drawable.cobansewu));
        recentsDataList.add(new RecentData("Candi Prambanan","Indonesia","Dimulai dari Rp 50.000",R.drawable.prambanan));

        setRecentRecycler(recentsDataList);

        List<PlacesData> PlacesDataList = new ArrayList<>();
        PlacesDataList.add(new PlacesData("Jakarta","Indonesia","Rp 1 - 3 Juta / hari",R.drawable.jakartaa));
        PlacesDataList.add(new PlacesData("Bandung","Indonesia","Rp 500 ribu - 2 Juta / hari",R.drawable.bandung));
        PlacesDataList.add(new PlacesData("Semarang","Indonesia","Rp 400 ribu - 1.5 Juta / hari",R.drawable.semarang));
        PlacesDataList.add(new PlacesData("Malang","Indonesia","Rp 700 ribu - 5 Juta / hari",R.drawable.malang));
        PlacesDataList.add(new PlacesData("Yogyakarta","Indonesia","Rp 200 ribu - 1 Juta / hari",R.drawable.yogya));

        setTopPlacesRecycler(PlacesDataList);
    }
    private  void setRecentRecycler(List<RecentData> recentsDataList){
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<PlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new PlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}