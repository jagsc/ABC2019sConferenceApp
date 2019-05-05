package com.example.abc2019sconferenceapp.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc2019sconferenceapp.Adapter.DetailAdapter;
import com.example.abc2019sconferenceapp.MainActivity;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

public class DetailFragment extends Fragment {
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }

        if (MainActivity.timelineData != null) {
            final TimelineDataBean.TimelineData timelineData = MainActivity.timelineData.getData().get(position);
            RecyclerView detailRecyclerView = v.findViewById(R.id.presenterRecyclerView);
            DetailAdapter detailAdapter = new DetailAdapter(timelineData, getFragmentManager(), this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            detailRecyclerView.setLayoutManager(layoutManager);
            detailRecyclerView.setAdapter(detailAdapter);

            TextView detailTitle = v.findViewById(R.id.detailTitle);
            detailTitle.setText(timelineData.getTitle());

            TextView detailTime = v.findViewById(R.id.detailTime);
            detailTime.setText(timelineData.getTime());

            TextView detailPlace = v.findViewById(R.id.detailPlace);
            detailPlace.setText(timelineData.getPlace() + "/" + timelineData.getCategory());

            TextView detailBody = v.findViewById(R.id.detailBody);
            detailBody.setText(timelineData.getBody());

            //発表スライドをViewごと追加
            for (int i = 0; i < timelineData.getSlideUrls().size(); i++) {
                final int pos = i;
                final TextView textView = new TextView(getContext());
                textView.setText("発表スライド" + String.valueOf(pos + 1));

                LinearLayout slideLayout = v.findViewById(R.id.slideLayout);

                slideLayout.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = timelineData.getSlideUrls().get(pos).getSlideurl();
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }

        }

        return v;
    }



}
