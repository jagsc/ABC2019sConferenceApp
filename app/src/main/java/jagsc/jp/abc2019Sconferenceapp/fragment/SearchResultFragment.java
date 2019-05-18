package jagsc.jp.abc2019Sconferenceapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jagsc.jp.abc2019Sconferenceapp.Adapter.TimelineAdapter;
import jagsc.jp.abc2019Sconferenceapp.MainActivity;
import jagsc.jp.abc2019Sconferenceapp.R;
import jagsc.jp.abc2019Sconferenceapp.TimelineDataBean;

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
                        break;
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
