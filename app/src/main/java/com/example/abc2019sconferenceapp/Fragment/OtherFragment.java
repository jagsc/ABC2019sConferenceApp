package com.example.abc2019sconferenceapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc2019sconferenceapp.R;

public class OtherFragment extends Fragment{
    TextView aboutABC;
    TextView blog;
    TextView map;
    TextView socialGathering;
    TextView registration;
    TextView inquiry;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_other, container, false);
        aboutABC = v.findViewById(R.id.about_abc);
        aboutABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/about");
            }
        });
        blog = v.findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/blog/");
            }
        });
        map = v.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://www.u-tokai.ac.jp/info/traffic_map/shared/pdf/takanawa_campus.pdf");
            }
        });
        socialGathering = v.findViewById(R.id.socialGathering);
        socialGathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc2019s-party.peatix.com/");
            }
        });
        registration = v.findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://japan-android-group.connpass.com/event/125928/");
            }
        });
        inquiry = v.findViewById(R.id.Inquiry);
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2019s/contact/");
            }
        });
        return v;
    }

    private void sendFragment(String url) {
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().add(R.id.setFragmentLayout, webViewFragment).addToBackStack(null).commit();
        }

    }

}
