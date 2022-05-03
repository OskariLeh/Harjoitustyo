package com.example.harjoitustyo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class LakeChosenActivity extends AppCompatActivity {

    TextView name;
    TextView town;
    TextView drainageBasin;
    TextView cordinates;
    TextView areaOfLake;
    TextView averageDepth;
    Lake lake;
    ImageButton addTripButton;
    ImageButton addToFavorites;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake2);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        addTripButton = (ImageButton) findViewById(R.id.imageButton4);
        addToFavorites = (ImageButton) findViewById(R.id.imageButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.name);
        name.setText(lake.getName());

        town = findViewById(R.id.town);
        town.setText("Kunta: "+lake.getTown());

        drainageBasin = findViewById(R.id.drainageBasin);
        drainageBasin.setText("Vesistöalue: "+lake.getDrainageBasin());

        cordinates = findViewById(R.id.cordinates);
        cordinates.setText("Koordinaatit desimaaleina: "+lake.getCordLat()+", "+lake.getCordLong());

        areaOfLake = findViewById(R.id.areaOfLake);
        areaOfLake.setText("Järven pinta-ala: "+Double.toString(lake.getAreaOfLake()));

        averageDepth = findViewById(R.id.averageDepth);
        areaOfLake.setText("Keskisyvyys: "+lake.getAverageDepth());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.imageButton4)) {
                    Intent intent = new Intent(LakeChosenActivity.this, LogTripActivity.class);
                    startActivity(intent);
                }
            }
        };

        addToFavorites.setOnClickListener(listener);
        addTripButton.setOnClickListener(listener);
    }

}