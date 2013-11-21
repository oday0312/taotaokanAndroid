package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-21
 * Time: PM3:16
 * To change this template use File | Settings | File Templates.
 */
public class SettingActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
    }

    public void onBackPressed() {
       finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

}


