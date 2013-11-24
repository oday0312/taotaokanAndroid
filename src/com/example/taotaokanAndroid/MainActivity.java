package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Gallery;
import android.widget.PopupWindow;
import com.example.taotaokanAndroid.gridView.GalleryImageAdapter;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private PopupWindow mPopupWindow;
    private Gallery myGallery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        CuzyAdSDK.getInstance().setContext(this);
        CuzyAdSDK.getInstance().registerApp("200003","208f53acd6d396867c2a721be6c807eb");



        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);


        actionBar.setTitle("淘淘看");
        actionBar.setHomeAction(new ActionBar.IntentAction(this, createIntent(this), R.drawable.ic_title_home_default));
        //actionBar.addAction(new ActionBar.IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default));
        actionBar.addAction(new ToastAction());

        myGallery = (Gallery)findViewById(R.id.gallery);
        //这段代码是杨丰盛老师的《android开发揭秘》中这样写的
        //myGallery.setBackgroundResource(R.drawable.bg0);
        GalleryImageAdapter adapter = new GalleryImageAdapter(this);
        //设置背景风格。Gallery背景风格定义在attrs.xml中
        TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
        adapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
        myGallery.setAdapter(adapter);




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
            if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(findViewById(R.layout.main), Gravity.BOTTOM, 0, 0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





    public Intent createIntent(Context context)
    {
        Intent t = new Intent();
        t.setClass(MainActivity.this, GridViewActivity.class);
        return t;
    }

    private class ToastAction implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.ic_title_export_default;
        }

        @Override
        public void performAction(View view) {

            ArrayList<CuzyTBKItem> rawData = CuzyAdSDK.getInstance().fetchRawItems("", "手机", 0);
            Log.d("huang alex", ""+rawData.size());
            //CuzyAdSDK.getInstance().("6","",0);

            //mPopupWindow.showAtLocation(findViewById(R.id.mainlayout), Gravity.BOTTOM, 0, 0);


//            Intent t = new Intent();
//            t.setClass(MainActivity.this,SettingActivity.class);
//            startActivity(t);
//            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


            //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);


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
