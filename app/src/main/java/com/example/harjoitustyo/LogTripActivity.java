package com.example.harjoitustyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class LogTripActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText time;
    EditText description;
    EditText duration;
    AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.imageButton4)) {

                }
            }
        };
    }
}
