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
import com.example.abc2019sconferenceapp.MainActivity;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;
import com.example.abc2019sconferenceapp.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_timeline, container, false);


        if (MainActivity.timelineData != null) {
            TimelineDataBean tmpTimelineDataBean = new TimelineDataBean();
            List<TimelineDataBean.TimelineData> tmpTimelinedata = new ArrayList<>();
            for (int i = 0; i < MainActivity.timelineData.getData().size(); i++) {
                TimelineDataBean.TimelineData timelineData = MainActivity.timelineData.getData().get(i);
                if (timelineData.getFavo() != null && timelineData.getFavo().equals("1")) {
                    tmpTimelinedata.add(timelineData);
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
