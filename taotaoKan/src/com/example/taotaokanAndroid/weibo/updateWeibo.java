package com.example.taotaokanAndroid.weibo;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 14-1-3
 * Time: PM6:26
 * To change this template use File | Settings | File Templates.
 */

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class updateWeibo extends AsyncTask<String, Void, Boolean> {

    //private TextView userInfo;
    private String result = "";
    private String accessToken="";
    private String shareString = "test";

    public updateWeibo(String accessTokenString, String inputShareString) {

        accessToken = accessTokenString;
        shareString = inputShareString;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean isGetInfo = false;
        String urlText = params[0];
        try {



            String path="https://api.weibo.com/2/statuses/update.json";
            HttpPost httpPost=new HttpPost(path);
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("access_token",accessToken ));
            list.add(new BasicNameValuePair("status", "test"));
            httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));//编者按：与HttpGet区别所在，这里是将参数用List传递

            String result="";

            HttpResponse response=new DefaultHttpClient().execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity=response.getEntity();
                result= EntityUtils.toString(entity, HTTP.UTF_8);
                Log.d("huangzf", "update weibo result " + result);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return isGetInfo;
    }

    @Override
    protected void onPostExecute(Boolean isGetInfo) {
        super.onPostExecute(isGetInfo);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}