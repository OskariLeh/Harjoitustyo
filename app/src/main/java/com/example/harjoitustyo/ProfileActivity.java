package com.example.harjoitustyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView profileName;
    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.textProfileName);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loggedIn = getIntent().getBooleanExtra("key", false);

        System.out.println(loggedIn);

        if (!loggedIn) {
            System.out.println("kökkö");
            Fragment fragment = new LoginFragment();
            FragmentManager fManager = getSupportFragmentManager();
            FragmentTransaction transaction = fManager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        return true;
    }
    // Handles the actions of toolbar buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_profile:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void closeActivity(){
        finish();
    }
}