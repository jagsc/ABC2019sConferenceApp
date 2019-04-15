package com.example.abc2019sconferenceapp;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

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
        String url = "http://www.ekidata.jp/api/p/23.json";//TODO ここのIDを変更する
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        String json = "";
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            json = body.string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
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
