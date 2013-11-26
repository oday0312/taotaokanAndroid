package com.example.taotaokanAndroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-5-19
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class webViewActivity extends Activity {


    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";

    public ProgressBar progressBar = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webview);
        progressBar = (ProgressBar)findViewById(R.id.webview_progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                .detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        if (getIntent().hasExtra(EXTRA_WEBURL)) {
            String title = getIntent().getStringExtra(EXTRA_WEBURL);
            //setTitle(title);
            WebView uiwebview = (WebView)findViewById(R.id.webView);
            uiwebview.setWebViewClient(new Callback());
            uiwebview.getSettings().setBuiltInZoomControls(true);
            uiwebview.getSettings().setJavaScriptEnabled(true);
            uiwebview.getSettings().setPluginsEnabled(true);
            uiwebview.getSettings().setPluginState(WebSettings.PluginState.ON);


            title = "http://" + title;
            uiwebview.loadUrl(title);
            uiwebview.setBackgroundColor(Color.BLACK);
            //uiwebview.getUrl();
            Log.v("huangzf", "the url is "+ title);
        }

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
//            view.loadUrl(url);
            return false;
        }
        @Override
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon){

            progressBar.setVisibility(View.VISIBLE);
            Log.d("CuzyAdSDK","started " + url);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.d("CuzyAdSDK","finished " + url);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            progressBar.setVisibility(View.INVISIBLE);
            Log.d("CuzyAdSDK","error " + failingUrl + " " + description);
        }
//        @Override
//        public void onLoadResource(android.webkit.WebView view, java.lang.String url) {
//
//        }
//        @Override
//        public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView view, java.lang.String url) {
//
//        }
    }
}