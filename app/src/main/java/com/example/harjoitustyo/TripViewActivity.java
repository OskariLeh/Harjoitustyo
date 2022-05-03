package com.example.harjoitustyo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//https://www.youtube.com/watch?v=17NbUcEts9c&ab_channel=CodinginFlow  Riikka
public class TripViewActivity extends AppCompatActivity {
    private  RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView name;
    TextView date;
    TextView duration;
    TextView location;
    Lake lake;
    Trip trip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        lake = (Lake) getIntent().getSerializableExtra("lake");

        recyclerView = findViewById(R.id.recycler_view_trip);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //adapter = new TripRecyclerViewAdapter(TripViewActivity.this); //what to inside the ()?

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        name = findViewById(R.id.lake_name);
        name.setText(lake.getName());

        date = findViewById(R.id.trip_date);
        date.setText(trip.getTime());

        duration = findViewById(R.id.trip_duration);
        duration.setText(trip.getDuration());

        location = findViewById(R.id.lake_location);
        location.setText(lake.getTown());

    }
}
