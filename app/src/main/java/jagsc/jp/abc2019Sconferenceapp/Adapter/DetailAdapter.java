package jagsc.jp.abc2019Sconferenceapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import jagsc.jp.abc2019Sconferenceapp.R;
import jagsc.jp.abc2019Sconferenceapp.TimelineDataBean;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailRecyclerViewHolder> {
    private TimelineDataBean.TimelineData dataBeans;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public DetailAdapter(TimelineDataBean.TimelineData dataBeans, FragmentManager fragmentManager, Fragment fragment) {
        this.dataBeans = dataBeans;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public DetailAdapter.DetailRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflateView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item, viewGroup, false);
        return new DetailAdapter.DetailRecyclerViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailAdapter.DetailRecyclerViewHolder viewHolder, int i) {
        if (dataBeans.getPresenterIcons() != null && dataBeans.getPresenterIcons().size() != 0) {
            Glide.with(fragment).load(dataBeans.getPresenterIcons().get(i).getIconUrl()).into(viewHolder.presenterIcon);
        }
        if (dataBeans.getPresenterNames() != null && dataBeans.getPresenterNames().size() != 0) {
            viewHolder.presenterName.setText(dataBeans.getPresenterNames().get(i).getPresenter());
        }
        if (dataBeans.getBelongs() != null && dataBeans.getBelongs().size() != 0) {
            viewHolder.companyName.setText(dataBeans.getBelongs().get(i).getBelong());
        }
    }

    @Override
    public int getItemCount() {
        if (dataBeans != null) {
            return dataBeans.getPresenterNames().size();
        } else {
            return 0;
        }
    }

    class DetailRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView presenterIcon;
        TextView presenterName;
        TextView companyName;

        private DetailRecyclerViewHolder(View itemView) {
            super(itemView);
            presenterIcon = itemView.findViewById(R.id.presenterIcon);
            presenterName = itemView.findViewById(R.id.presenterName);
            companyName = itemView.findViewById(R.id.companyName);
        }
    }
}
