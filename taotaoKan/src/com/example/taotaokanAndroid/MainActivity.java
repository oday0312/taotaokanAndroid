package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
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
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
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

        addSNSshare();

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);


        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);


        actionBar.setTitle("淘淘看");
        //actionBar.setHomeAction(new ActionBar.IntentAction(this, createIntent(this), R.drawable.ic_title_home_default));
        actionBar.addAction(new StartSearchAction());
        actionBar.addAction(new StartSettingAction());
        actionBar.addAction(new StartShareGridView());
        actionBar.addAction(new StartFavorViewAction());


        //start to get the big picture information
        new LongOperation().execute();
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
            return R.drawable.button_shoppingcar;
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
            mController.openShare(MainActivity.this, false);
         }

    }
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share",
            RequestType.SOCIAL);
    public void addSNSshare()
    {
        //hidden weixin
        //String appID = "wx775e75a07668f1a1";
        //String contentUrl = "http://.umeng.com/social";
        //mController.getConfig().supportWXPlatform(this,appID, contentUrl);
       // mController.getConfig().supportWXCirclePlatform(this,appID, contentUrl) ;

        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
        mController.setShareMedia(new UMImage(this,"http://www.umeng.com/images/pic/banner_module_social.png"));
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    private class LongOperation extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params){

            TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();
            rawData.clear();
            rawData = CuzyAdSDK.getInstance().fetchRawItems("1669", "", 0);
            application.wareItemsArray.clear();

            Log.d("huang alex", ""+rawData.size());
            for (int i = 0; i< rawData.size();i++)
            {
                CuzyTBKItem cuzyData = rawData.get(i);
                WaresItems temp = new WaresItems();
                temp.itemClickURLString =  cuzyData.getItemClickURLString();
                temp.itemDescription = cuzyData.getItemDescription();
                temp.itemPrice = cuzyData.getItemPrice();

                temp.itemImageURLString = cuzyData.getItemImageURLString();
                temp.itemFreePostage= cuzyData.getItemFreePostage();
                temp.itemName = cuzyData.getItemName();

                temp.itemPromotionPrice = cuzyData.getItemPromotionPrice();
                temp.itemType = cuzyData.getItemType();
                temp.tradingVolumeInThirtyDays= cuzyData.getTradingVolumeInThirtyDays();
                application.wareItemsArray.add(temp);
            }




            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //mAdapter = new TestFragmentAdapter(getSupportFragmentManager());
            mAdapter.notifyDataSetChanged();
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
