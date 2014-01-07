package com.theindex.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-5-19
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class webViewActivity extends Activity {


    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";
    public static final String EXTRA_TITLE_SHOW = "com.devspark.sidenavigation.meiriyiwen.extra.title.show";
    public static final String EXTRA_TITLE_TEXT = "com.devspark.sidenavigation.meiriyiwen.extra.title.text";


    public WebView uiwebview;
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


        if (getIntent().hasExtra(EXTRA_TITLE_SHOW))
        {
            RelativeLayout titleBar = (RelativeLayout)findViewById(R.id.include);
            titleBar.setVisibility(View.VISIBLE);
        }
        else
        {
            RelativeLayout titleBar = (RelativeLayout)findViewById(R.id.include);
            titleBar.setVisibility(View.INVISIBLE);
        }

        if (getIntent().hasExtra(EXTRA_TITLE_TEXT))
        {
            String textString = getIntent().getStringExtra(EXTRA_TITLE_TEXT);
            TextView titleView = (TextView)findViewById(R.id.titleText);
            titleView.setText(textString);
        }


        if (getIntent().hasExtra(EXTRA_WEBURL)) {
            String title = getIntent().getStringExtra(EXTRA_WEBURL);
            //setTitle(title);
            uiwebview = (WebView)findViewById(R.id.webView);
            uiwebview.setWebViewClient(new Callback());
            uiwebview.getSettings().setBuiltInZoomControls(true);
            uiwebview.getSettings().setJavaScriptEnabled(true);
            uiwebview.getSettings().setPluginsEnabled(true);
            uiwebview.getSettings().setPluginState(WebSettings.PluginState.ON);




            //String userAgentString = "Mozilla/5.0 (Linux; Android 4.1.1; M040 Build/JRO03H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36";
            //uiwebview.getSettings().setUserAgentString("" +
                  // userAgentString);

            if (title.contains("http"))
            {

            }
            else
            {
                title = "http://" + title;

            }
            uiwebview.loadUrl(title);
            //uiwebview.loadUrl(title);
            Log.v("huangzf", "the url is "+ title);
        }

    }

    public void removeSmartTAOBAOad()
    {
        //uiwebview.loadUrl("javascript:var J_wrapper = document.getElementById('smartAd');  document.body.removeChild(J_wrapper);");

        uiwebview.loadUrl("javascript:setTimeout(\\\"document.getElementById('smartAd').style.display = 'none'; \\\",1000);");


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
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.INVISIBLE);
            removeSmartTAOBAOad();
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            progressBar.setVisibility(View.INVISIBLE);

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