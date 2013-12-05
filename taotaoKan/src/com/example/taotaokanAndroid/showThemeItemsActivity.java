package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;
import com.theindex.CuzyAdSDK.CuzyAdSDK;
import com.theindex.CuzyAdSDK.CuzyTBKItem;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-5
 * Time: PM5:16
 * To change this template use File | Settings | File Templates.
 */
public class showThemeItemsActivity extends Activity implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
    PullToRefreshView mPullToRefreshView;
    public GridView gridView;

    //图片的第一行文字
    private String[] titles = new String[]
            { "美女卷珠帘", "美女回眸", "美女很有趣", "美女醉酒", "美女微笑", "美女如脱兔", "美女柳叶弯眉"};
    //图片的第二行文字
    private String[] description = new String[]
            { "啦啦啦", "嘎嘎嘎", "哇哇哇", "喵喵喵", "刚刚刚", "当当当", "咔咔咔"};
    //图片ID数组
    private int[] images = {
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy };



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_theme_items);
        mPullToRefreshView = (PullToRefreshView)findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        gridView  = (GridView)findViewById(R.id.gridview_show_theme_items);
        GridItemType1Adapter Gridadapter = new GridItemType1Adapter(titles, images,description,this);
        gridView.setAdapter(Gridadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(showThemeItemsActivity.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getThemeItems()
    {
        rawData = CuzyAdSDK.getInstance().fetchRawItems("", "女装", 0);
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

    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {

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



}