package com.example.abc2019sconferenceapp.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.abc2019sconferenceapp.R;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.webview_item, container, false);

        final WebView webView = v.findViewById(R.id.webView);

        webView.loadUrl("https://abc.android-group.jp/2018s/app/#conference");
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                /*ページの読み込みが始まった時の処理*/
                Log.i("MainActivity : ", "読み込み開始");
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                /*ページの読み込みが終わった時の処理*/
                Log.i("MainActivity : ", "読み込み終了");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl)
            {
                /*読み込めなかったときの処理*/
                Log.i("MainActivity : ", "読み込み失敗");
            }
        });

        return v;
    }



}
