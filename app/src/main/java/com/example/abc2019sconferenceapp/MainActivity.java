package com.example.abc2019sconferenceapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.abc2019sconferenceapp.fragment.BazzarFragment;
import com.example.abc2019sconferenceapp.fragment.FavoriteFragment;
import com.example.abc2019sconferenceapp.fragment.OtherFragment;
import com.example.abc2019sconferenceapp.fragment.TimelineFragment;
import com.example.abc2019sconferenceapp.fragment.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    public static TimelineDataBean timelineData = null;//Jsonデータは動的に変わることがないので、MainActivityだけで1回読み込んで、各Fragmentからデータを読み出すようにする
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");//TODO アプリのIDに変える
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");//TODD アプリのnameに変える
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
          if (mFirebaseAnalytics != null) {
              mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);            
          }

        bottomNavigationView = findViewById(R.id.navigation);

        String url = "https://prprhyt.github.io/ABC2019sConferenceApp/time-table.json";
        //JsonDataを読み込む
        HTTPResponse httpResponse = new HTTPResponse(url);
        httpResponse.setOnCallBack(new HTTPResponse.CallBackTask() {
            @Override
            public void Callback(Object o) {
                JSONObject jsonObject = (JSONObject) o;
                Gson gson = new Gson();
                timelineData = gson.fromJson(jsonObject.toString(), TimelineDataBean.class);//TimelineDataBeanにデータが入る
                Log.d("TAG","非同期処理が終了しました。");
                setFavo();
                setSelectedListener();
            }
        });
        httpResponse.execute(AsyncTask.SERIAL_EXECUTOR);//非同期処理を実行
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Jsonデータを文字列としてSharedPreferenceに保存しておく。
        if (timelineData != null) {
            Gson gson = new Gson();
            Utils.saveJson(this, gson.toJson(timelineData));
        }
    }

    //古いJsonデータからお気に入り情報を新しいJsonデータにセットする
    private void setFavo() {
        String oldJson = Utils.getJson(this);
        if (!oldJson.equals("")) {
            //TODO もう少し効率化したい
            try {
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(Utils.getJson(this));
                TimelineDataBean oldTimelineDataBean = gson.fromJson(jsonObject.toString(), TimelineDataBean.class);
                for (int i = 0; i < oldTimelineDataBean.getData().size(); i++) {
                    for (int j = 0; j < timelineData.getData().size(); j++) {
                        if (oldTimelineDataBean.getData().get(i).getItemID().equals(timelineData.getData().get(j).getItemID())) { //古いデータと新しいデータのItemIDが一致していたらお気に入り情報をセットする
                            timelineData.getData().get(j).setFavo(oldTimelineDataBean.getData().get(i).getFavo());
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (timelineData != null) {
                for (int i = 0; i < timelineData.getData().size(); i++) {
                    if (timelineData.getData().get(i).getFavo() == null) {
                        timelineData.getData().get(i).setFavo("0");
                    }
                }
            }
        }
    }

    private void setSelectedListener() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new TimelineFragment()).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                switch (menuItem.getItemId()) {
                    case R.id.timeline:
                        int timelineCount = fragmentManager.getBackStackEntryCount();
                        for (int i = 0; i < timelineCount; i++) {
                            fragmentManager.popBackStack();
                        }
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new TimelineFragment()).commitAllowingStateLoss();
                        return true;
                    case R.id.search:
                        int searchCount = fragmentManager.getBackStackEntryCount();
                        for (int i = 0; i < searchCount; i++) {
                            fragmentManager.popBackStack();
                        }
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new SearchFragment()).commitAllowingStateLoss();
                        return true;
                    case R.id.bazzar:
                        int bazzarCount = fragmentManager.getBackStackEntryCount();
                        for (int i = 0; i < bazzarCount; i++) {
                            fragmentManager.popBackStack();
                        }
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new BazzarFragment()).commitAllowingStateLoss();
                        return true;
                    case R.id.favorite:
                        int favoriteCount = fragmentManager.getBackStackEntryCount();
                        for (int i = 0; i < favoriteCount; i++) {
                            fragmentManager.popBackStack();
                        }
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new FavoriteFragment()).commitAllowingStateLoss();
                        return true;
                    case R.id.other:
                        int otherCount = fragmentManager.getBackStackEntryCount();
                        for (int i = 0; i < otherCount; i++) {
                            fragmentManager.popBackStack();
                        }
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, new OtherFragment()).commitAllowingStateLoss();
                        return true;
                }
                return false;
            }
        });
    }
}
