package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import com.example.taotaokanAndroid.LoadMoreListView.LoadMoreListView;
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

    private  SearchView search;
    private RelativeLayout searchbarView;
    public ArrayList<CuzyTBKItem> rawData = new ArrayList<CuzyTBKItem>();

    public LoadMoreListView listView;
    public ArrayList<PaperItem> DataArray = new ArrayList<PaperItem>();
    public  cuzyAdapter adapter = null;
    public ImageLoader imageLoader=  null;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);

        search = (SearchView)findViewById(R.id.searchView);
        search.setFocusable(true);
        search.setIconified(false);
        search.requestFocusFromTouch();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchRequested();
                Log.d("huang alex" , "begin search " + query);
                rawData = CuzyAdSDK.getInstance().fetchRawItems("", "手机", 0);
                Log.d("huang alex", ""+rawData.size());
                for (int i = 0; i< rawData.size();i++)
                {
                    CuzyTBKItem cuzyData = rawData.get(i);
                    PaperItem temp = new PaperItem();
                    temp.urlString =  cuzyData.getItemClickURLString();
                    temp.contentString = cuzyData.getItemDescription();
                    temp.titleString = cuzyData.getItemPrice();
                    temp.imageString = cuzyData.getItemImageURLString();
                    DataArray.add(temp);
                }

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
                //new LoadDataTask().execute();
            }
        });

        testSimpleListView();
    }

    public void testSimpleListView()
    {

        imageLoader=new ImageLoader(this);
        adapter = new cuzyAdapter(DataArray, this,this, imageLoader,1);
        listView.setAdapter(adapter);


    }

    private class LongOperation extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String...params){




            return"Executed";
        }

        @Override
        protected void onPostExecute(String result){
            //reloadListView();
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