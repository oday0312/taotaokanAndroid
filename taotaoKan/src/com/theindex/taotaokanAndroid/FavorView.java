package com.theindex.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.theindex.taotaokanAndroid.SQLiteHelper.WaresItemsDataUtil;
import com.theindex.taotaokanAndroid.gridView.GridItemType2FavorAdapter;
import com.theindex.taotaokanAndroid.imageCache.ImageLoader;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-29
 * Time: AM10:29
 * To change this template use File | Settings | File Templates.
 */
public class FavorView extends Activity {
    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";
    public static final String EXTRA_TITLE_SHOW = "com.devspark.sidenavigation.meiriyiwen.extra.title.show";
    public static final String EXTRA_TITLE_TEXT = "com.devspark.sidenavigation.meiriyiwen.extra.title.text";


    private GridView gridView;


    public GridItemType2FavorAdapter adapter2;
    public ImageLoader imageLoader;

    public WaresItemsDataUtil dbUtil;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorview);

        dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();

        View v = this.findViewById(android.R.id.content).getRootView();
        TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();
        Random random = new Random();
        int t =  Math.abs(random.nextInt()) % (application.images.length);
        Drawable d = getResources().getDrawable(application.images[t]);
        try
        {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                v.setBackgroundDrawable(d);
            } else {
                v.setBackground(d);
            }
        }catch (Exception e)
        {
            ;
        }


        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("收藏");

        gridView = (GridView) findViewById(R.id.favorview_gridview);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //Toast.makeText(FavorView.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
                WaresItems w =  adapter2.gridItemList.get(position);
                startWebViewActivity(w.itemClickURLString);
            }
        });

        gridView.setLongClickable(true);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {

                 WaresItems temp = adapter2.gridItemList.get(position);
                 removeItems(temp, position);

                //set the image as wallpaper
                return true;
            }
        });

        testSQLite();
    }

    public void removeItems(WaresItems temp,int position)
    {
        dbUtil.deleteStudent(temp.itemImageURLString);

        adapter2.gridItemList.remove(position);
        adapter2.notifyDataSetChanged();
        Toast.makeText(this, "收藏已经删除", Toast.LENGTH_SHORT).show();
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
        if(t.size()==0)
        {
            //do not have any data
            Toast.makeText(this, "你还没有收藏任何商品", Toast.LENGTH_SHORT).show();
        }
    }


    public void onBackPressed() {
        dbUtil.close();
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
}
