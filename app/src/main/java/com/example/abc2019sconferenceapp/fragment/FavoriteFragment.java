package com.example.abc2019sconferenceapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.MainActivity;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
