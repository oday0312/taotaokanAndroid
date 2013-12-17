package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.example.taotaokanAndroid.LoadMoreListView.LoadMoreListView;
import com.example.taotaokanAndroid.SQLiteHelper.WaresItemsDataUtil;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import com.example.taotaokanAndroid.imageCache.*;
/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-25
 * Time: PM6:01
 * To change this template use File | Settings | File Templates.
 */
public class SearchActivity extends Activity {

    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";
    public static final String EXTRA_PAPERITEM = "com.devspark.sidenavigation.meiriyiwen.extra.paperitem";

    private  SearchView search;
    private RelativeLayout searchbarView;
    public ArrayList<CuzyTBKItem> rawData = new ArrayList<CuzyTBKItem>();

    public LoadMoreListView listView;
    public ArrayList<WaresItems> DataArray = new ArrayList<WaresItems>();
    public  cuzyAdapter adapter = null;
    public ImageLoader imageLoader=  null;
    public WaresItemsDataUtil dbUtil;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);

        dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();

        search = (SearchView)findViewById(R.id.searchView);
        search.setFocusable(true);
        search.setIconified(false);
        search.requestFocusFromTouch();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchRequested();
                Log.d("huang alex" , "begin search " + query);
                rawData = CuzyAdSDK.getInstance().fetchRawItems("", "女装", 0);
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

                    DataArray.add(temp);

                    dbUtil.createStudent(temp );

                }



                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                return false;
            }
        });

       ImageButton bt1 = (ImageButton)findViewById(R.id.searchtoobarButton1);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Log.d("huang alex", "iamge button 1 pressed");
            }
        });


        listView = (LoadMoreListView)findViewById(R.id.searchview_listview);
        listView.setDividerHeight(0);
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            public void onLoadMore() {
                // Do the work to load more items at the end of list
                // here
                Log.v("alex huang", "on load more called");
                new LongOperation().execute();
            }
        });

        testSimpleListView();

        // Click on ListView Row:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Object o = listView.getItemAtPosition(position);
                WaresItems tempItem = DataArray.get(position);
                Log.i("alex huang ", tempItem.itemClickURLString);
                //startWebViewActivity(tempItem.urlString);
                startDetailViewLevel1(tempItem);
            }
        });

    }

    public void testSimpleListView()
    {

        imageLoader=new ImageLoader(this);
        adapter = new cuzyAdapter(DataArray, this,this, imageLoader,1);
        listView.setAdapter(adapter);


    }

    public void reloadListView()
    {
        adapter.notifyDataSetChanged();
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


    public void startWebViewActivity(String urlString)
    {
        Intent intent = new Intent(this, webViewActivity.class);
        intent.putExtra(EXTRA_WEBURL, urlString);

        // all of the other activities on top of it will be closed and this
        // Intent will be delivered to the (now on top) old activity as a
        // new Intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
    }
    static int pageIndex = 1;
    private class LongOperation extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void...params){

            rawData.clear();
            rawData = CuzyAdSDK.getInstance().fetchRawItems("", "女装", pageIndex);
            pageIndex++;
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
                DataArray.add(temp);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            reloadListView();
            //simpleWindowADadapter.notifyDataSetChanged();
            listView.onLoadMoreComplete();
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            //((LoadMoreListView) getListView()).onLoadMoreComplete();
            listView.onLoadMoreComplete();;
        }

        @Override
        protected void onPreExecute(){
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values){
        }
    }
    public void onBackPressed() {
        dbUtil.close();
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