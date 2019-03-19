package com.example.abc2019sconferenceapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.abc2019sconferenceapp.Fragment.FavoriteFragment;
import com.example.abc2019sconferenceapp.Fragment.OtherFragment;
import com.example.abc2019sconferenceapp.Fragment.SearchFragment;
import com.example.abc2019sconferenceapp.Fragment.TimelineFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                switch (menuItem.getItemId()) {
                    case R.id.timeline:
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new TimelineFragment()).commit();
                        return true;
                    case R.id.search:
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new SearchFragment()).commit();
                        return true;
                    case R.id.favorite:
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new FavoriteFragment()).commit();
                        return true;
                    case R.id.other:
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new OtherFragment()).commit();
                        return true;
                }
                return false;
            }
        });

    }
}

