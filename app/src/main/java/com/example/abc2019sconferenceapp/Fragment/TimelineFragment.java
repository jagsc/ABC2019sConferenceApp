package com.example.abc2019sconferenceapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.HTTPResponse;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TimelineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 先ほどのレイアウトをここでViewとして作成します
        final View v = inflater.inflate(R.layout.fragment_timeline, container, false);

        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.setOnCallBack(new HTTPResponse.CallBackTask() {
            @Override
            public void Callback(Object o) {
                //TODO GsonでTimelineBeanにいい感じにデータを代入する
//                Gson gson = new Gson();
//                TimelineDataBean timelineDataBean = gson.fromJson((String)o, TimelineDataBean.class);
//                timelineDataBean.getBelongs();

                List<TimelineDataBean> dataBeans = new ArrayList<>();//TimelineDataBeanに代入



                //非同期が終わった後にここの処理が実行される
                RecyclerView timelineRecyclerView = v.findViewById(R.id.timelineRecyclerview);
                TimelineAdapter timelineAdapter = new TimelineAdapter(dataBeans, v.getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                timelineRecyclerView.setLayoutManager(layoutManager);
                timelineRecyclerView.setAdapter(timelineAdapter);
                Log.d("TAG","非同期処理が終了しました。");
            }
        });
        httpResponse.execute();//非同期処理を実行
        return v;
    }

    //検証用のダミーデータ作成
    public List<TimelineDataBean> createTmpDataset() {
        List<TimelineDataBean> dataBeans = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            TimelineDataBean timelineDataBean = new TimelineDataBean();
//            timelineDataBean.setName("name" + String.valueOf(i));
            timelineDataBean.setTitle("title" + String.valueOf(i));
            timelineDataBean.setTime("time" + String.valueOf(i));
            timelineDataBean.setPlace("Place" + String.valueOf(i));

            dataBeans.add(timelineDataBean);
        }
        return dataBeans;
    }
}
