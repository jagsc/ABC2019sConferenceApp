package com.example.abc2019sconferenceapp;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HTTPResponse extends AsyncTask {

    private CallBackTask callBackTask;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //Test用のJson
        String Json = "{\"version\": \"1\", \"data\": [{\"itemID\": \"0001\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0002\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0003\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}, {\"itemID\": \"0004\", \"title\": \"講演タイトル\", \"body\": \"講演内容です。\\n複数行に渡る場合があります。\\n講演内容がない場合はありません。\", \"presenterNames\": [{\"presenter\": \"発表太郎\"}, {\"presenter\": \"発表次郎\"}], \"belongs\": [{\"belong\": \"K大学\"}, {\"belong\": \"T大学\"}], \"place\": \"1階第1研究室\", \"time\": \"11:00-11:45\", \"tags\": [{\"tag\": \"Android\"}, {\"tag\": \"Flutter\"}, {\"tag\": \"iOS\"}]}]}";

        String url = "http://www.ekidata.jp/api/p/23.json";//TODO ここのIDを変更する
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        TimelineDataBean timelineDataBean = null;
        try {
//            Response response = client.newCall(request).execute();
//            ResponseBody body = response.body();
//            String json = body.string();//これがAPIを通じて帰ってきたString型のJsonデータ

            JSONObject jsonObject = new  JSONObject(Json.toString());//Json形式の文字列をJsonObjectに変換する //TODO テスト用Jsonではなく、Github上のファイルを読み込めるようにする
            Gson gson = new Gson();
            timelineDataBean = gson.fromJson(jsonObject.toString(), TimelineDataBean.class);//TimelineDataBeanにデータが入る
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timelineDataBean;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        callBackTask.Callback(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public void setOnCallBack(CallBackTask cbt) {
        callBackTask = cbt;
    }

    public static class CallBackTask {
        public void Callback(Object o) {

        }
    }
}
