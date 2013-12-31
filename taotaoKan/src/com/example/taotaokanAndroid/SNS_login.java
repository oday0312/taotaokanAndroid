package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.*;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-31
 * Time: PM1:13
 * To change this template use File | Settings | File Templates.
 */
public class SNS_login extends Activity {

    private Tencent mTencent;
    BaseUiListener listener = new BaseUiListener();
    private String appidString ="100587628";


    private String weiboAPPid = "3619755693";
    private String callbackUrl = "http://www.cuzy.com";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_login);
        mTencent = Tencent.createInstance(appidString, this.getApplicationContext());


        Button bt1 = (Button)findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }
    private class BaseUiListener implements IUiListener {

        @Override

        public void onComplete(JSONObject response) {

            //mBaseMessageText.setText("onComplete:");

            //mMessageText.setText(response.toString());

            doComplete(response);

        }

        protected void doComplete(JSONObject values) {
            Log.v("huangzf do complete", values.toString());
            getUserInfoInThread();

        }

        @Override

        public void onError(UiError e) {

            Log.e("onError","code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:" + e.errorDetail);

        }

        @Override

        public void onCancel() {

            Log.e("onCancel", "");

        }

    }

    public void login()
    {
        if (!mTencent.isSessionValid())
        {
            mTencent.login(this,"all" , listener);

            mTencent.login(this,"get_simple_userinfo" , listener);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResult(requestCode, resultCode, data);
    }

    public void getUserInfo()
    {
        //mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
        //        Constants.HTTP_GET, new BaseApiListener("get_simple_userinfo", false), null);
    }
    public void getUserInfoInThread()
    {
        new Thread(){
            @Override
            public void run() {
                JSONObject json = mTencent.request(Constants.GRAPH_SIMPLE_USER_INFO, null,
                        Constants.HTTP_GET);

                Log.d("huangzf", json.toString());
            }
        }.start();
    }







    private class BaseApiListener implements IRequestListener {

        @Override

        public void onComplete(final JSONObject response, Object state) {

            //showResult("IRequestListener.onComplete:", response.toString());

            doComplete(response, state);

        }

        protected void doComplete(JSONObject response, Object state) {

        }

        @Override

        public void onIOException(final IOException e, Object state) {

            //showResult("IRequestListener.onIOException:", e.getMessage());

        }

        @Override

        public void onMalformedURLException(final MalformedURLException e,

                                            Object state) {

            //showResult("IRequestListener.onMalformedURLException", e.toString());

        }

        @Override

        public void onJSONException(final JSONException e, Object state) {

            //showResult("IRequestListener.onJSONException:", e.getMessage());

        }

        @Override

        public void onConnectTimeoutException(ConnectTimeoutException arg0,

                                              Object arg1) {



        }

        @Override

        public void onSocketTimeoutException(SocketTimeoutException arg0,

                                             Object arg1) {



        }


        @Override

        public void onNetworkUnavailableException(NetworkUnavailableException e, Object state){


        }

        @Override

        public void onHttpStatusException(HttpStatusException e, Object state) {


        }

        public void onUnknowException(Exception e, Object state) {


        }

    }
}