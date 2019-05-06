package com.example.abc2019sconferenceapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc2019sconferenceapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class OtherFragment extends Fragment{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_other, container, false);
        TextView aboutABC = v.findViewById(R.id.about_abc);
        aboutABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/about");
            }
        });
        TextView blog = v.findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/blog/");
            }
        });
        TextView map = v.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://www.u-tokai.ac.jp/info/traffic_map/shared/pdf/takanawa_campus.pdf");
            }
        });
        TextView socialGathering = v.findViewById(R.id.socialGathering);
        socialGathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc2019s-party.peatix.com/");
            }
        });
        TextView registration = v.findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://japan-android-group.connpass.com/event/125928/");
            }
        });
        TextView inquiry = v.findViewById(R.id.Inquiry);
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/contact/");
            }
        });
        return v;
    }

    private void sendFragment(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
