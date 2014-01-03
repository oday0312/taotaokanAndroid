package com.example.taotaokanAndroid.weibo;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 14-1-3
 * Time: PM6:08
 * To change this template use File | Settings | File Templates.
 */
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class GetUserInfoTask extends AsyncTask<String, Void, Boolean> {

    //private TextView userInfo;
    private String result = "";

    public GetUserInfoTask() {
        //this.userInfo = userInfo;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean isGetInfo = false;
        String urlText = params[0];
        try {
            URL url = new URL(urlText);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {//若当前连接成功
                isGetInfo = true;
                InputStream inStream = conn.getInputStream();//打开输入流
                while ((len = inStream.read(data)) != -1) {
                    outStream.write(data, 0, len);
                }
                result = new String(outStream.toByteArray());//新建result变量用于获取服务器端传回的字符串
                Log.d("huangzf", "result = " + result);
                inStream.close();//关闭数据输入流
            }
            outStream.close();//关闭数据输出流
            conn.disconnect();//关闭远程连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isGetInfo;
    }

    @Override
    protected void onPostExecute(Boolean isGetInfo) {
        super.onPostExecute(isGetInfo);
        if (isGetInfo) {
            Userparser userparser = new Userparser();
            weiboUser bean = userparser.userInfoParser(result);
            //userInfo.setText(bean.toString());
        } else {
            //userInfo.setText("false --- ");
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}