package com.example.abc2019sconferenceapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc2019sconferenceapp.Adapter.BazzarAdapter;
import com.example.abc2019sconferenceapp.BazzarDataBean;
import com.example.abc2019sconferenceapp.HTTPResponse;
import com.example.abc2019sconferenceapp.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class BazzarFragment extends Fragment {

    private RecyclerView bazzarRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_bazzar, container, false);
        bazzarRecyclerView = v.findViewById(R.id.bazzarRecyclerView);

        String url = "https://jagsc.github.io/ABC2019sConferenceApp/bazzar.json";
        //JsonDataを読み込む
        HTTPResponse httpResponse = new HTTPResponse(url);
        httpResponse.setOnCallBack(new HTTPResponse.CallBackTask() {
            @Override
            public void Callback(Object o) {
                JSONObject jsonObject = (JSONObject)o;
                Gson gson = new Gson();
                BazzarDataBean bazzarDataBean = gson.fromJson(jsonObject.toString(), BazzarDataBean.class);
                setAdapter(bazzarDataBean);
            }
        });
        httpResponse.execute(AsyncTask.SERIAL_EXECUTOR);//非同期処理を実行
        return v;
    }

    private void setAdapter(BazzarDataBean bazzarDataBean) {
        if (bazzarDataBean != null) {
            BazzarAdapter bazzarAdapter = new BazzarAdapter(bazzarDataBean, getFragmentManager(), this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            bazzarRecyclerView.setLayoutManager(layoutManager);
            bazzarRecyclerView.setAdapter(bazzarAdapter);
        }

    }
}
