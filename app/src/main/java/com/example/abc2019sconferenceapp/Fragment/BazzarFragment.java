package com.example.abc2019sconferenceapp.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc2019sconferenceapp.Adapter.BazzarAdapter;
import com.example.abc2019sconferenceapp.Adapter.TimelineAdapter;
import com.example.abc2019sconferenceapp.BazzarDataBean;
import com.example.abc2019sconferenceapp.HTTPResponse;
import com.example.abc2019sconferenceapp.MainActivity;
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

        //TODO URLを書き換える
        String url = "{\"version\": \"1\", \"data\": [{\"boothNum\": \"1\", \"title\": \"ブースタイトル\", \"body\": \"ブースの内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"companyName\": \"ABC会社\", \"companyIcon\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"boothNum\": \"2\", \"title\": \"ブースタイトル\", \"body\": \"ブースの内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"companyName\": \"ABC会社\", \"companyIcon\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"boothNum\": \"3\", \"title\": \"ブースタイトル\", \"body\": \"ブースの内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"companyName\": \"ABC会社\", \"companyIcon\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"boothNum\": \"4\", \"title\": \"ブースタイトル\", \"body\": \"ブースの内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"companyName\": \"ABC会社\", \"companyIcon\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}, {\"boothNum\": \"5\", \"title\": \"ブースタイトル\", \"body\": \"ブースの内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"companyName\": \"ABC会社\", \"companyIcon\": \"https://pbs.twimg.com/media/D4_XIPYUUAEYVtX.jpg\"}]}";
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
