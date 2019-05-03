package com.example.abc2019sconferenceapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.MainActivity;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment {
    String keyWard = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_timeline, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            keyWard = bundle.getString("key");
            if (keyWard != null) {
                keyWard = keyWard.trim();
            }
        }

        if (MainActivity.timelineData != null) {
            TimelineDataBean tmpTimelineDataBean = new TimelineDataBean();
            List<TimelineDataBean.TimelineData> tmpTimelinedata = new ArrayList<>();
            for (int i = 0; i < MainActivity.timelineData.getData().size(); i++) {
                TimelineDataBean.TimelineData timelineData = MainActivity.timelineData.getData().get(i);
                //検索用にTimelineDataBeanに入っているテキストをすべて1つ1つallTextに代入していく。
                List<String> allText = new ArrayList<>();
                allText.add(timelineData.getTitle());
                allText.add(timelineData.getBody());
                allText.add(timelineData.getPlace());
                allText.add(timelineData.getTime());
                for (int j = 0; j < timelineData.getPresenterNames().size(); j++) {
                    allText.add(timelineData.getPresenterNames().get(j).getPresenter());
                }
                for (int j = 0; j < timelineData.getBelongs().size(); j ++) {
                    allText.add(timelineData.getBelongs().get(j).getBelong());
                }
                for (int j = 0; j < timelineData.getTags().size(); j++) {
                    allText.add(timelineData.getTags().get(j).getTag());
                }
                for (int j = 0; j < allText.size(); j++) {
                    if (allText.get(j).contains(keyWard)) {
                        tmpTimelinedata.add(timelineData);
                    }
                }
            }
            tmpTimelineDataBean.setData(tmpTimelinedata);

            RecyclerView timelineRecyclerView = v.findViewById(R.id.timelineRecyclerview);

            TimelineAdapter timelineAdapter = new TimelineAdapter(tmpTimelineDataBean, getFragmentManager(), this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            timelineRecyclerView.setLayoutManager(layoutManager);
            timelineRecyclerView.setAdapter(timelineAdapter);
        }
        return v;
    }
}
