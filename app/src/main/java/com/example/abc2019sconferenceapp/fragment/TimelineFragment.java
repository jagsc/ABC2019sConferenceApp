package com.example.abc2019sconferenceapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.MainActivity;
import com.example.abc2019sconferenceapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TimelineFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 先ほどのレイアウトをここでViewとして作成します
        final View v = inflater.inflate(R.layout.fragment_timeline, container, false);

        if (MainActivity.timelineData != null) {
            RecyclerView timelineRecyclerView = v.findViewById(R.id.timelineRecyclerview);
            TimelineAdapter timelineAdapter = new TimelineAdapter(MainActivity.timelineData, getFragmentManager(), this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            timelineRecyclerView.setLayoutManager(layoutManager);
            timelineRecyclerView.setAdapter(timelineAdapter);
        }
        return v;
    }
}
