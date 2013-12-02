package com.example.taotaokanAndroid;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.SQLiteHelper.WaresItemsDataUtil;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;
import com.example.taotaokanAndroid.gridView.GridItemType2FavorAdapter;
import com.example.taotaokanAndroid.imageCache.ImageLoader;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-29
 * Time: AM10:29
 * To change this template use File | Settings | File Templates.
 */
public class FavorView extends Activity {
    private GridView gridView;
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

    public GridItemType1Adapter adapter;

    public GridItemType2FavorAdapter adapter2;
    public ImageLoader imageLoader;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorview);

        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("收藏");

        gridView = (GridView) findViewById(R.id.favorview_gridview);

        adapter = new GridItemType1Adapter(titles, images,description,this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(FavorView.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        testSQLite();
    }



    /*KEY_ROWID,
    KEY_itemClickURLString,
    KEY_itemDescription,
    KEY_itemFreePostage,
    KEY_itemImageURLString,
    KEY_itemName,
    KEY_itemPrice,
    KEY_itemPromotionPrice,
    KEY_itemType,
    KEY_tradingVolumeInThirtyDays
    */
    public void testSQLite()
    {
        //插入
        WaresItemsDataUtil dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();
        Cursor cursor = dbUtil.fetchAllStudents();


        imageLoader=new ImageLoader(this);

        ArrayList<WaresItems> t = new ArrayList<WaresItems>();
        gridView.setAdapter(adapter2);
        if(cursor != null){
            while(cursor.moveToNext()){
                WaresItems temp = new WaresItems();
                temp.itemClickURLString = cursor.getString(1);
                temp.itemDescription = cursor.getString(2);
                temp.itemFreePostage = cursor.getString(3);
                temp.itemImageURLString = cursor.getString(4);

                temp.itemName = cursor.getString(5);
                temp.itemPrice = cursor.getString(6);
                temp.itemPromotionPrice = cursor.getString(7);
                temp.itemType = cursor.getString(8);
                temp.tradingVolumeInThirtyDays = cursor.getString(9);
                t.add(temp);

            }
        }
        adapter2 = new GridItemType2FavorAdapter(t, imageLoader,this);
        gridView.setAdapter(adapter2);
        dbUtil.close();
    }
}
