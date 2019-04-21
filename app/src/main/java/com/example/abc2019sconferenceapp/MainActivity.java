package com.example.abc2019sconferenceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.example.abc2019sconferenceapp.Fragment.FavoriteFragment;
import com.example.abc2019sconferenceapp.Fragment.OtherFragment;
import com.example.abc2019sconferenceapp.Fragment.SearchFragment;
import com.example.abc2019sconferenceapp.Fragment.TimelineFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    public static TimelineDataBean timelineData = null;//Jsonデータは動的に変わることがないので、MainActivityだけで1回読み込んで、各Fragmentからデータを読み出すようにする
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");//TODO アプリのIDに変える
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");//TODD アプリのnameに変える
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        bottomNavigationView = findViewById(R.id.navigation);

        //JsonDataを読み込む
        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.setOnCallBack(new HTTPResponse.CallBackTask() {
            @Override
            public void Callback(Object o) {
                timelineData = (TimelineDataBean) o;
                Log.d("TAG","非同期処理が終了しました。");
                setSelectedListener();
            }
        });
        httpResponse.execute(AsyncTask.SERIAL_EXECUTOR);//非同期処理を実行
    }

    private void setSelectedListener() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new TimelineFragment()).commit();

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

