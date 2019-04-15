package com.example.abc2019sconferenceapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    String keyWard = "";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_timeline, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyWard = bundle.getString("key");
        }
        //TODO keywardを使ってデータをフィルタリングする
        RecyclerView timelineRecyclerView = v.findViewById(R.id.timelineRecyclerview);

        //TODO テスト用データから本場用データに切り替える
//                TimelineAdapter timelineAdapter = new TimelineAdapter(dataBeans, v.getContext());
        TimelineAdapter timelineAdapter = new TimelineAdapter(createTmpDataset(), getFragmentManager());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        timelineRecyclerView.setLayoutManager(layoutManager);
        timelineRecyclerView.setAdapter(timelineAdapter);

        return v;
    }

    //検証用のダミーデータ作成
    public List<TimelineDataBean> createTmpDataset() {
        List<TimelineDataBean> dataBeans = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                TimelineDataBean timelineDataBean = new TimelineDataBean();
                timelineDataBean.setItemID(String.valueOf(i));
                timelineDataBean.setPlace("場所" + String.valueOf(i));
                timelineDataBean.setTime("11:0" + String.valueOf(i));
                timelineDataBean.setTitle("タイトル" + String.valueOf(i));
                dataBeans.add(timelineDataBean);
            }
        }
        return dataBeans;
    }
}
