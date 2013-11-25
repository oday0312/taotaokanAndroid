package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;
import com.umeng.analytics.MobclickAgent;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-25
 * Time: PM6:01
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);

        SearchView search = (SearchView)findViewById(R.id.searchView);
        search.setFocusable(true);
        search.setIconified(false);
        search.requestFocusFromTouch();
    }




    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}