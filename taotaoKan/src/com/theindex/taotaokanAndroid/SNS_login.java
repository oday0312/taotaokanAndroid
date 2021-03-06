package com.theindex.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;
import com.theindex.taotaokanAndroid.weibo.WeiboGetUserInfoTask;
import com.theindex.taotaokanAndroid.weibo.updateWeibo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.*;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-31
 * Time: PM1:13
 * To change this template use File | Settings | File Templates.
 *  public static final String SCOPE =
 "email,direct_messages_read,direct_messages_write,"
 + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
 + "follow_app_official_microblog," + "invitation_write";
 */



public class SNS_login extends Activity {


    private String nickName = "";
    private String avatarUrl = "";



    private Tencent mTencent;
    BaseUiListener listener = new BaseUiListener();
    private String appidString ="100587628";


    private String WEIBO_APP_KEY = "3619755693";
    private String WEIBO_REDIRECT_URL = "http://www.cuzy.com";
    private String WEIBO_SCOPE=   "all";
             //"follow_app_official_microblog";

    private WeiboAuth mWeiboAuth;
    private Oauth2AccessToken mAccessToken;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_login);
        mTencent = Tencent.createInstance(appidString, this.getApplicationContext());


        mWeiboAuth = new WeiboAuth(this, WEIBO_APP_KEY, WEIBO_REDIRECT_URL, WEIBO_SCOPE);


        Button bt1 = (Button)findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tencentlogin();
            }
        });


        Button bt2 = (Button)findViewById(R.id.button1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weiboLogin();
            }
        });

        Button bt3 = (Button)findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainAcitivty();
            }
        });

    }


    private void weiboLogin()
    {
        mWeiboAuth.anthorize(new AuthDialogListener());
    }
    class AuthDialogListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences

               //AccessTokenKeeper.writeAccessToken(SNS_login.this, mAccessToken);
                String uidString = values.getString("uid");
                String userName = values.getString("userName");
                String expires_in = values.getString("expires_in");

                String access_token = values.getString("access_token");
                String code = values.getString("code", "");
                Log.d("huangzf", uidString + " " + userName + " "+ access_token + code);
                mAccessToken = new Oauth2AccessToken(access_token, expires_in);
                if (mAccessToken.isSessionValid()) {
                    String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(new java.util.Date(mAccessToken
                                    .getExpiresTime()));

                 }

                TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();

                WeiboGetUserInfoTask task = new WeiboGetUserInfoTask(application);
                String url = "https://api.weibo.com/2/users/show.json?uid=" + uidString + "&access_token=" + access_token;

                task.execute(url);


                updateWeibo task2 =new updateWeibo(access_token,"");
                task2.execute("");

                startMainAcitivty();

            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
                Log.d("huangzf", "token is session invalid "+ code);
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.d("huangzf", "weibo exception" + e.toString());
        }
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
            TencentGetUserInfoInThread();

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

    public void Tencentlogin()
    {
        if (true)//(!mTencent.isSessionValid())
        {
            mTencent.login(this,"all" , listener);

            //mTencent.login(this,"get_simple_userinfo" , listener);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResult(requestCode, resultCode, data);
        startMainAcitivty();

    }

    public void getUserInfo()
    {
        //mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
        //        Constants.HTTP_GET, new BaseApiListener("get_simple_userinfo", false), null);
    }

    public void TencentGetUserInfoInThread()
    {
        new Thread(){
            @Override
            public void run() {
                JSONObject json = mTencent.request(Constants.GRAPH_SIMPLE_USER_INFO, null,
                        Constants.HTTP_GET);

                Log.d("huangzf", json.toString());
                TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();

                try{
                    avatarUrl = json.getString("figureurl_qq_1");
                    nickName = json.getString("nickname");
                    application.nickName = nickName;
                    application.AvatarUrl = avatarUrl;

                }
                catch (Exception e)
                {

                }




            }
        }.start();
    }



    public void startMainAcitivty()
    {
        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
        finish();
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






    private class LongOperation extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params){


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {



        }

        @Override
        protected void onCancelled() {
        }

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected void onProgressUpdate(Void... values){
        }
    }
}