package com.theindex.taotaokanAndroid;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-6-16
 * Time: 下午8:50
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.theindex.taotaokanAndroid.imageCache.*;
public class cuzyAdapter extends BaseAdapter {

    private ArrayList<WaresItems> items = null;
    private Context             context       = null;
    private Activity thisActivity = null;

    private int listViewtype = 0;
    public ImageLoader imageLoader=  null;

    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.yeeyanAndroid.extra.weburl";
    /**
     * 构造函数,初始化Adapter,将数据传入
     * @param items
     * @param context
     */
    public cuzyAdapter(ArrayList<WaresItems> items,
                       Context context,
                       Activity adapterActivity,
                       ImageLoader imageLoader,
                       int listViewType)
    {
        this.items = items;
        this.context = context;
        this.thisActivity = adapterActivity;
        this.imageLoader = imageLoader;
        this.listViewtype = listViewType;


        //for test

    }

    @Override
    public int getCount() {

        int length = items.size()/2;
        if (listViewtype == 0)
        {

        }
        else
        {
            length = items.size();
        }


        return length;
    }

    @Override
    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final View view;

        //装载view
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = createType1View(position,convertView,parent);

        return view;
    }


    public View createType1View(int position, View convertView, ViewGroup parent)
    {
        final View view;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.shengyinglist, null);
        view.setBackgroundColor(Color.parseColor("#00000000"));

        RelativeLayout layout = (RelativeLayout)view.findViewById(R.id.secondlevellayout);


        final WaresItems waresItems = (WaresItems) getItem(position);
        //获取控件
        final ImageView itemImageView = (ImageView) view.findViewById(R.id.listImageView);
        TextView listTitle = (TextView) view.findViewById(R.id.listTitle);
        TextView listContent = (TextView) view.findViewById(R.id.listContent);
        TextView listContent2 =(TextView)view.findViewById(R.id.listContent2);

        final Bitmap temp = getRes("");
        imageLoader.DisplayImage(waresItems.itemImageURLString , itemImageView);
        listTitle.setText(waresItems.itemName);
        listContent2.setText("" + waresItems.itemPromotionPrice +"元" );

        listContent.setText("30天销售"+ waresItems.tradingVolumeInThirtyDays+"件");



        return view;
    }









    public Bitmap getRes(String name) {

        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
        return bMap;
    }



}