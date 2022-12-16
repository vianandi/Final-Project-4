package com.mbkm.project4;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mbkm.project4.Model.BusModel;
import com.mbkm.project4.Model.PlacesData;
import com.mbkm.project4.Model.RecentData;
import com.mbkm.project4.adapter.BusAdapter;
import com.mbkm.project4.adapter.PlacesAdapter;
import com.mbkm.project4.adapter.RecentAdapter;
import com.mbkm.project4.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ListBusActivity extends AppCompatActivity {

    RecyclerView recyclerBus;
    BusAdapter busAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bus);

        List<BusModel> busModelList = new ArrayList<>();
        busModelList.add(new BusModel("PO Haryanto","Executive","Seat : 2 - 2","Dimulai dari Rp 220.000",R.drawable.haryanto2));
        busModelList.add(new BusModel("PO Nusantara","Super Executive","Seat : 2 - 1","Dimulai dari Rp 260.000",R.drawable.nusantara));
        busModelList.add(new BusModel("PO Shantika","Super Executive","Seat : 2 - 1","Dimulai dari Rp 250.000",R.drawable.shantika1));
        busModelList.add(new BusModel("PO Agra Mas","Double Decker","Seat : 2 - 2","Dimulai dari Rp 400.000",R.drawable.agramas));
        busModelList.add(new BusModel("PO Bejeu","Executive","Seat : 2 - 2","Dimulai dari Rp 200.000",R.drawable.bejeu2));
        busModelList.add(new BusModel("PO Pahala Kencana","Super Executive","Seat : 2 - 1","Dimulai dari Rp 350.000",R.drawable.pahalakencana2));

        setupToolbar();
        setRecyclerBus(busModelList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbListBus);
        toolbar.setTitle("Bus Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private  void setRecyclerBus(List<BusModel> busModelList){

        recyclerBus = findViewById(R.id.recyclerBus);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerBus.setLayoutManager(layoutManager);
        busAdapter = new BusAdapter(this, busModelList);
        recyclerBus.setAdapter(busAdapter);

    }
}