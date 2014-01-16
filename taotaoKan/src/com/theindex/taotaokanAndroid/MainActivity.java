package com.theindex.taotaokanAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.theindex.taotaokanAndroid.zxing.barcodeActivity;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends BaseSampleActivity implements MainActivityFragment_toolbar.OnMyButton1ClickListener {


    /**
     * Called when the activity is first created.
     */

    private Random RANDOM = new Random();
    private PopupWindow mPopupWindow;

    public ArrayList<CuzyTBKItem> rawData = new ArrayList<CuzyTBKItem>();
    public MainActivityFragment_toolbar toolbar;
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
        ActionBar.Action barAction = new startBarCodeAction();
        actionBar.addAction(barAction);
        actionBar.addAction(new StartSearchAction());
        actionBar.addAction(new StartSettingAction());
        actionBar.addAction(new StartShareGridView());
        actionBar.addAction(new StartFavorViewAction());





        //start to get the big picture information
        new LongOperation().execute();







    }


    public void AddToolBar()
    {
        toolbar = MainActivityFragment_toolbar.newInstance();
        Bundle args = new Bundle();
        //args.putInt(MainActivityFragment_toolbar.ARG);
        toolbar.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container,toolbar);
        transaction.commit();

    }
    public void removeToolBar()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.hide(toolbar);
        Log.d("huangzf", "remove toolbar");
        transaction.commit();
    }
    @Override
    public void onMyButton1Click(String themeidString) {

        LongOperationButtonSelection thread1 = new LongOperationButtonSelection();
        thread1.themeID = themeidString;

        thread1.execute();
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
        //t.setClass(barcodeActivity.this, GridViewActivity.class);
        t.setClass(MainActivity.this, SearchActivity.class);

        return t;
    }

    public static int toolbarStatus = 0;
    private class startBarCodeAction implements ActionBar.Action
    {
        @Override
        public int getDrawable() {
            return R.drawable.button_barcode;
        }

        @Override
        public void performAction(View view) {
            //Intent t = new Intent();
            //t.setClass(MainActivity.this,barcodeActivity.class);
            //startActivity(t);
            //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            if (toolbarStatus == 0)
            {
                AddToolBar();
                toolbarStatus = 1;
            }
            else
            {
                removeToolBar();
                toolbarStatus = 0;
            }
        }
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

        mController.setShareContent("淘淘看 由cuzysdk提供购物导航数据 http://www.cuzy.com");
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
            SystemClock.sleep(500);
            while(CuzyAdSDK.getInstance().registerResult==0)
            {
                 SystemClock.sleep(100);
            }

            int tempInter = RANDOM.nextInt(100);
            int offset = tempInter% application.wareItemsArray.size();
            String cuzyThemeString = application.cuzyThemeStrings[0+offset];
            Log.d("huang", "the cuzytheme string is " + cuzyThemeString );

            {
                rawData = CuzyAdSDK.getInstance().fetchRawItems(cuzyThemeString, "", 0);

            }

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


                int tempRandint2 = RANDOM.nextInt(100);
                //if (tempRandint2 % 2 == 0)
                {
                    application.wareItemsArray.add(temp);

                }
                Log.d("huang alex", ""+ temp.toString() );
            }




            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

            mAdapter.notifyDataSetChanged();
            mPager = (ViewPager)findViewById(R.id.pager);
            mPager.setAdapter(mAdapter);


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




    private class LongOperationButtonSelection extends AsyncTask<Void,Void,Void> {

        public String themeID = "1";
        @Override
        protected Void doInBackground(Void...params){

            TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();
            rawData.clear();

            String cuzyThemeString = themeID;

            {
                rawData = CuzyAdSDK.getInstance().fetchRawItems(cuzyThemeString, "", 0);

            }

            application.wareItemsArray.clear();

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
                Log.d("huang alex", ""+ temp.toString() );
            }




            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

            mAdapter.notifyDataSetChanged();
            mPager = (ViewPager)findViewById(R.id.pager);
            mPager.setAdapter(mAdapter);


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
