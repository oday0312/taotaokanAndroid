package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.gridView.GalleryImageAdapter;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;
import com.umeng.analytics.MobclickAgent;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;

public class MainActivity extends BaseSampleActivity {


    /**
     * Called when the activity is first created.
     */


    private PopupWindow mPopupWindow;

    public ArrayList<CuzyTBKItem> rawData = new ArrayList<CuzyTBKItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        CuzyAdSDK.getInstance().setContext(this);
        CuzyAdSDK.getInstance().registerApp("200003","208f53acd6d396867c2a721be6c807eb");



        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);


        actionBar.setTitle("淘淘看");
        //actionBar.setHomeAction(new ActionBar.IntentAction(this, createIntent(this), R.drawable.ic_title_home_default));
        actionBar.addAction(new StartSearchAction());
        actionBar.addAction(new StartSettingAction());
        actionBar.addAction(new StartShareGridView());
        actionBar.addAction(new StartFavorViewAction());






        View popupView = getLayoutInflater().inflate(R.layout.share_popup_windows, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);
        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });




    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





    public Intent createIntent(Context context)
    {
        Intent t = new Intent();
        //t.setClass(MainActivity.this, GridViewActivity.class);
        t.setClass(MainActivity.this, SearchActivity.class);

        return t;
    }

    private class StartFavorViewAction implements ActionBar.Action{
        @Override
        public int getDrawable() {
            return R.drawable.button_setting_normal;
        }

        @Override
        public void performAction(View view) {
            Intent t = new Intent();
            t.setClass(MainActivity.this,FavorView.class);
            startActivity(t);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }

    private class StartSettingAction implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.button_setting_normal;
        }

        @Override
        public void performAction(View view) {
            Intent t = new Intent();
            t.setClass(MainActivity.this,SettingActivity.class);
            startActivity(t);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }

    }

    private class StartSearchAction implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.button_storesearch_normal;
        }

        @Override
        public void performAction(View view) {
            Intent t = new Intent();
            t.setClass(MainActivity.this,SearchActivity.class);
            startActivity(t);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }

    }


    private class StartShareGridView implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.button_share_normal;
        }

        @Override
        public void performAction(View view) {
           mPopupWindow.showAtLocation(findViewById(R.id.mainlayout), Gravity.BOTTOM, 0, 0);

        }

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
