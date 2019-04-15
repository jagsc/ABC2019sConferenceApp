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
    TextView notification;
    TextView map;
    TextView survey;
    TextView inquiry;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_other, container, false);
        aboutABC = v.findViewById(R.id.about_abc);
        aboutABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2018s/about/");
            }
        });
        notification = v.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        map = v.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        survey = v.findViewById(R.id.survey);
        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        inquiry = v.findViewById(R.id.Inquiry);
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFragment("https://abc.android-group.jp/2018s/contact/");
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
