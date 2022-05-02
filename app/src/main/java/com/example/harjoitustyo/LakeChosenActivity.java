package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

        name = findViewById(R.id.name);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        name.setText(lake.getName());

        town = findViewById(R.id.town);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        town.setText("Kunta: "+lake.getTown());

        drainageBasin = findViewById(R.id.drainageBasin);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        drainageBasin.setText("Vesistöalue: "+lake.getDrainageBasin());

        cordinates = findViewById(R.id.cordinates);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        cordinates.setText("Koordinaatit desimaaleina: "+lake.getCordLat()+", "+lake.getCordLong());

        areaOfLake = findViewById(R.id.areaOfLake);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        areaOfLake.setText("Järven pinta-ala: "+Double.toString(lake.getAreaOfLake()));

        averageDepth = findViewById(R.id.averageDepth);
        lake = (Lake) getIntent().getSerializableExtra("lake");
        areaOfLake.setText("Keskisyvyys: "+lake.getAverageDepth());
    }
}