package jagsc.jp.abc2019Sconferenceapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import jagsc.jp.abc2019Sconferenceapp.BazzarDataBean;
import jagsc.jp.abc2019Sconferenceapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class BazzarAdapter extends RecyclerView.Adapter<BazzarAdapter.BazzarRecyclerViewHolder> {

    private BazzarDataBean dataBeans;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public BazzarAdapter(BazzarDataBean dataBeans, FragmentManager fragmentManager, Fragment fragment) {
        this.dataBeans = dataBeans;
        this.fragmentManager = fragmentManager;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public BazzarRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflateView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bazzar_item, viewGroup, false);
        return new BazzarRecyclerViewHolder(inflateView);
    }

    @Override
    public void onBindViewHolder(@NonNull BazzarRecyclerViewHolder viewHolder, int i) {
        final BazzarDataBean.BazzarData itemList = dataBeans.getData().get(i);
        viewHolder.title.setText(itemList.getTitle());
        viewHolder.boothNum.setText("ブース番号:" + itemList.getBoothNum());
        viewHolder.companyName.setText("出展者名:" + itemList.getCompanyName());
        viewHolder.body.setText(itemList.getBody());
        Glide.with(fragment).load(itemList.getCompanyIcon()).into(viewHolder.companyIcon);
    }

    @Override
    public int getItemCount() {
        if (dataBeans.getData() != null) {
            return dataBeans.getData().size();
        } else {
            return 0;
        }
    }

    class BazzarRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView boothNum;
        TextView title;
        TextView body;
        TextView companyName;
        ImageView companyIcon;

        private BazzarRecyclerViewHolder(View itemView) {
            super(itemView);
            boothNum = itemView.findViewById(R.id.boothNum);
            title = itemView.findViewById(R.id.boothTitle);
            body = itemView.findViewById(R.id.boothBody);
            companyName = itemView.findViewById(R.id.companyName);
            companyIcon = itemView.findViewById(R.id.companyIcon);
        }
    }
}
