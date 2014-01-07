package com.theindex.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.theindex.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.theindex.taotaokanAndroid.gridView.GridItemShowThemeItemsAdapter;
import com.theindex.taotaokanAndroid.imageCache.ImageLoader;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-5
 * Time: PM5:16
 * To change this template use File | Settings | File Templates.
 */
public class showThemeItemsActivity extends Activity implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{

    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";
    public static final String EXTRA_TITLE_SHOW = "com.devspark.sidenavigation.meiriyiwen.extra.title.show";
    public static final String EXTRA_TITLE_TEXT = "com.devspark.sidenavigation.meiriyiwen.extra.title.text";
    public static final String EXTRA_PAPERITEM = "com.devspark.sidenavigation.meiriyiwen.extra.paperitem";



    public static final String EXTRA_Theme_String = "THEME_STRING";


    public String SearchKeyString = "";
    public String ThemeString = "1";

    PullToRefreshView mPullToRefreshView;
    public GridView gridView;
    public ArrayList<CuzyTBKItem> rawData = new ArrayList<CuzyTBKItem>();
    public ArrayList<WaresItems> DataArray = new ArrayList<WaresItems>();



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_theme_items);
        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("主题详情");

        if (getIntent().hasExtra(EXTRA_Theme_String))
        {
            ThemeString = getIntent().getStringExtra(EXTRA_Theme_String);
        }

        mPullToRefreshView = (PullToRefreshView)findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        gridView  = (GridView)findViewById(R.id.gridview_show_theme_items);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //Toast.makeText(showThemeItemsActivity.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
                WaresItems wareitem = DataArray.get(position);
                startDetailViewLevel1(wareitem);
            }
        });


        adapter = new GridItemShowThemeItemsAdapter(DataArray,imageLoader,this);
        gridView.setAdapter(adapter);

        new LongOperation().execute();
    }

    public ImageLoader imageLoader = new ImageLoader(this);
    public int pageIndex = 0;
    public GridItemShowThemeItemsAdapter adapter;
    public void getThemeItems()
    {
        rawData = CuzyAdSDK.getInstance().fetchRawItems(ThemeString, "", pageIndex);
        Log.d("huang alex", "" + rawData.size());

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

            DataArray.add(temp);



        }
        //adapter = new GridItemShowThemeItemsAdapter(DataArray,imageLoader,this);
        //gridView.setAdapter(adapter);
    }

    public void getMoreThemeItems()
    {
        pageIndex++;
        rawData = CuzyAdSDK.getInstance().fetchRawItems(ThemeString, "", pageIndex);
        Log.d("huang alex", "" + rawData.size());

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

            DataArray.add(temp);



        }
        Log.d("cuzy ", "data array count is"+DataArray.size());
        adapter.notifyDataSetChanged();



    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {

                getMoreThemeItems();
                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1000);
    }
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 璁剧疆鏇存柊鏃堕棿
                //mPullToRefreshView.onHeaderRefreshComplete("鏈�繎鏇存柊:01-23 12:01");
                mPullToRefreshView.onHeaderRefreshComplete();
            }
        },1000);

    }

    public void startWebViewActivity(String urlString)
    {
        Intent intent = new Intent(this, webViewActivity.class);
        intent.putExtra(EXTRA_WEBURL, urlString);
        intent.putExtra(EXTRA_TITLE_SHOW,"true");
        intent.putExtra(EXTRA_TITLE_TEXT, "");
        // all of the other activities on top of it will be closed and this
        // Intent will be delivered to the (now on top) old activity as a
        // new Intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
    }
    public void startDetailViewLevel1(WaresItems item)
    {
        Intent intent = new Intent(this, DetailViewLevel1.class);
        intent.putExtra(EXTRA_PAPERITEM, item);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
    }


    private class LongOperation extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params){

            getThemeItems();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.notifyDataSetChanged();
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