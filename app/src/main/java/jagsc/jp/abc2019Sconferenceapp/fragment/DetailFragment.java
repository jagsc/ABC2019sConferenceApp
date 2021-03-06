package jagsc.jp.abc2019Sconferenceapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import jagsc.jp.abc2019Sconferenceapp.Adapter.DetailAdapter;
import jagsc.jp.abc2019Sconferenceapp.MainActivity;
import jagsc.jp.abc2019Sconferenceapp.R;
import jagsc.jp.abc2019Sconferenceapp.TimelineDataBean;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailFragment extends Fragment {
    private String itemID;


    private TimelineDataBean.TimelineData getTimelineDataFromItemID(String itemID){
        for(TimelineDataBean.TimelineData d:MainActivity.timelineData.getData()){
            if(null==d || null==d.getItemID()){
                continue;
            }
            if(d.getItemID().equals(itemID)){
                return d;
            }
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            itemID = bundle.getString("itemID");
        }

        if (MainActivity.timelineData != null) {
            final TimelineDataBean.TimelineData timelineData = getTimelineDataFromItemID(itemID);
            if(null==timelineData){
                return v;
            }
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
                        if (timelineData.getSlideUrls()!= null && timelineData.getSlideUrls().get(pos).getSlideurl() != null) {
                            String url = timelineData.getSlideUrls().get(pos).getSlideurl();
                            if (!url.equals("")) {
                                Uri uri = Uri.parse(url);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                    }
                });
            }

        }

        return v;
    }



}
