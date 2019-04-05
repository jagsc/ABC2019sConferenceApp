package com.example.abc2019sconferenceapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc2019sconferenceapp.Fragment.SearchResultFragment;
import com.example.abc2019sconferenceapp.Fragment.TimelineFragment;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineRecyclerViewHolder> {
    private List<TimelineDataBean> dataBeans;
    private Context context;

    public TimelineAdapter(List<TimelineDataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public TimelineRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflateView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timeline_item, viewGroup, false);
        return new TimelineRecyclerViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TimelineRecyclerViewHolder viewHolder, int i) {
//        viewHolder.name.setText(dataBeans.get(i).getName());
        viewHolder.time.setText(dataBeans.get(i).getTime());
        viewHolder.title.setText(dataBeans.get(i).getTitle());
        viewHolder.place.setText(dataBeans.get(i).getPlace());
//        viewHolder.timelineCell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = fragment.getFragmentManager();
//                if(fragmentManager != null) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    // BackStackを設定
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.replace(R.id.container, new SearchResultFragment());
//                    fragmentTransaction.commit();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class TimelineRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView title;
        TextView name;
        TextView place;
        ConstraintLayout timelineCell;

        private TimelineRecyclerViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeText);
            title = itemView.findViewById(R.id.title_text);
            name = itemView.findViewById(R.id.name_text);
            place = itemView.findViewById(R.id.place_text);
            timelineCell = itemView.findViewById(R.id.timelineCell);
        }
    }
}
