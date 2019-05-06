package com.example.abc2019sconferenceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.abc2019sconferenceapp.Fragment.BazzarFragment;
import com.example.abc2019sconferenceapp.Fragment.FavoriteFragment;
import com.example.abc2019sconferenceapp.Fragment.OtherFragment;
import com.example.abc2019sconferenceapp.Fragment.SearchFragment;
import com.example.abc2019sconferenceapp.Fragment.TimelineFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.internal.Util;

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

        //TODO URLを書き換える
        String url = "{\"version\": \"1\", \"data\": [{\"itemID\": \"0001\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"presenterIcons\": [{\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIlIU8AEqmAa.jpg\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"slideUrls\": [{\"slideurl\": \"https://www.slideshare.net/akirasasaki1/android-things-101696989\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"category\": \"keynote\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0002\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"presenterIcons\": [{\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIlIU8AEqmAa.jpg\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"slideUrls\": [{\"slideurl\": \"https://www.slideshare.net/akirasasaki1/android-things-101696989\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"category\": \"keynote\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0003\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"presenterIcons\": [{\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIlIU8AEqmAa.jpg\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"slideUrls\": [{\"slideurl\": \"https://www.slideshare.net/akirasasaki1/android-things-101696989\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"category\": \"keynote\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0004\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"presenterIcons\": [{\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIlIU8AEqmAa.jpg\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"slideUrls\": [{\"slideurl\": \"https://www.slideshare.net/akirasasaki1/android-things-101696989\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"category\": \"keynote\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0005\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"presenterIcons\": [{\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"iconUrl\": \"https://pbs.twimg.com/media/D4_XIlIU8AEqmAa.jpg\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"slideUrls\": [{\"slideurl\": \"https://www.slideshare.net/akirasasaki1/android-things-101696989\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"category\": \"keynote\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}]}";
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

