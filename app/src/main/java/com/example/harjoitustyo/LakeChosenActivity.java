package com.example.harjoitustyo;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake2);
        lake = (Lake) getIntent().getSerializableExtra("lake");

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
    }

}