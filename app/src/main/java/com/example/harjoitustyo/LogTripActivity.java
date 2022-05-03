package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class LogTripActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText time;
    EditText description;
    EditText duration;
    AppManager appManager;
    Button button;
    UserManager userManager;
    Lake lake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        time = findViewById(R.id.editTextTime);
        description = findViewById(R.id.editTextDescription);
        duration = findViewById(R.id.editTextDuration);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = findViewById(R.id.buttonAddTrip);
        appManager = new AppManager(LogTripActivity.this);
        userManager = (UserManager) getIntent().getSerializableExtra("manager");
        lake = (Lake) getIntent().getSerializableExtra("lake");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.buttonAddTrip)) {
                    Trip trip = new Trip(lake.getName(), description.getText().toString(), duration.getText().toString(), time.getText().toString());
                    appManager.addTrip(trip);
                    appManager.saveTripsAndFavorites(userManager.getUser());
                }
            }
        };
        button.setOnClickListener(listener);
    }
}
