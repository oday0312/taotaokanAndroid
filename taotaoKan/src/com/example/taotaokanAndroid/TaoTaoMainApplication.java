package com.example.taotaokanAndroid;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-4
 * Time: PM4:57
 * To change this template use File | Settings | File Templates.
 */
public class TaoTaoMainApplication extends Application {

    public final String SHARE_FILE_NAME = "taotaokan";
    public final String BACKGROUND_KEY = "desktop background";


    public int backgroundResourceID= R.drawable.bg_source_1;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sp = getSharedPreferences(SHARE_FILE_NAME, MODE_PRIVATE);
        String backString  = sp.getString(BACKGROUND_KEY,"");
        if (backString.length()>0)
        {
            backgroundResourceID = Integer.parseInt(backString);
        }

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
