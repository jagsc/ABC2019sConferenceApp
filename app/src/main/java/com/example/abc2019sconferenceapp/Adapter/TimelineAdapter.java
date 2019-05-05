package com.example.abc2019sconferenceapp.Adapter;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abc2019sconferenceapp.Fragment.DetailFragment;
import com.example.abc2019sconferenceapp.Fragment.SearchResultFragment;
import com.example.abc2019sconferenceapp.Fragment.TimelineFragment;
import com.example.abc2019sconferenceapp.R;
import com.example.abc2019sconferenceapp.TimelineDataBean;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineRecyclerViewHolder> {
    private TimelineDataBean dataBeans;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public TimelineAdapter(TimelineDataBean dataBeans, FragmentManager fragmentManager, Fragment fragment) {
        this.dataBeans = dataBeans;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public TimelineRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflateView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timeline_item, viewGroup, false);
        return new TimelineRecyclerViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TimelineRecyclerViewHolder viewHolder, int i) {
        final TimelineDataBean.TimelineData itemList = dataBeans.getData().get(i);
        final int position = i;
        viewHolder.time.setText(itemList.getTime());
        viewHolder.title.setText(itemList.getTitle());
        viewHolder.place.setText(itemList.getPlace());
        if (itemList.getFavo() != null && itemList.getFavo().equals("1")) {
            Glide.with(fragment).load(R.drawable.baseline_star_rate_black_36).into(viewHolder.favo);
        } else if (itemList.getFavo() != null && itemList.getFavo().equals("0")) {
            Glide.with(fragment).load(R.drawable.baseline_star_border_black_36).into(viewHolder.favo);
        }
        //講演者名を追加
        String presenterName = "";
        for (int j = 0; j < itemList.getPresenterNames().size(); j++) {
            presenterName += itemList.getPresenterNames().get(j).getPresenter() + " ";
        }
        viewHolder.name.setText(presenterName);

        //tagをViewごと追加
        for (int j = 0; j < itemList.getTags().size(); j++) {
            final TextView textView = new TextView(fragment.getContext());
            textView.setText(itemList.getTags().get(j).getTag() + "  ");

            viewHolder.tagLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String keyWard = textView.getText().toString();
                    if (fragmentManager != null && !keyWard.equals("")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("key", keyWard);
                        SearchResultFragment searchResultFragment = new SearchResultFragment();
                        searchResultFragment.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.setFragmentLayout, searchResultFragment).commit();
                    }
                }
            });
        }

        //ファボボタンをタップしたらTimelineDataBeanのfavoに1をセット
        viewHolder.favo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemList.getFavo() != null) {
                    if (itemList.getFavo().equals("0")) {
                        itemList.setFavo("1");
                        Glide.with(fragment).load(R.drawable.baseline_star_rate_black_36).into(viewHolder.favo);
                    } else if (itemList.getFavo().equals("1")) {
                        itemList.setFavo("0");
                        Glide.with(fragment).load(R.drawable.baseline_star_border_black_36).into(viewHolder.favo);
                    }
                }
            }
        });
        viewHolder.timelineCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentManager != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.setFragmentLayout, detailFragment).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataBeans.getData() != null) {
            return dataBeans.getData().size();
        } else {
            return 0;
        }
    }

    class TimelineRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView title;
        TextView name;
        TextView place;
        ImageButton favo;
        LinearLayout tagLayout;
        ConstraintLayout timelineCell;

        private TimelineRecyclerViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.timeText);
            title = itemView.findViewById(R.id.title_text);
            name = itemView.findViewById(R.id.name_text);
            place = itemView.findViewById(R.id.place_text);
            timelineCell = itemView.findViewById(R.id.timelineCell);
            favo = itemView.findViewById(R.id.favo_button);
            tagLayout = itemView.findViewById(R.id.tagLayout);
        }
    }
}
