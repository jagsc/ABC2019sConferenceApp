package com.example.abc2019sconferenceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.abc2019sconferenceapp.fragment.FavoriteFragment;
import com.example.abc2019sconferenceapp.fragment.OtherFragment;
import com.example.abc2019sconferenceapp.fragment.search.SearchFragment;
import com.example.abc2019sconferenceapp.fragment.TimelineFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
  private FirebaseAnalytics mFirebaseAnalytics;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");//TODO アプリのIDに変える
    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");//TODD アプリのnameに変える
    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
    if (mFirebaseAnalytics != null) {
      mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }


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

