package com.example.taotaokanAndroid.gridView;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 14-1-4
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taotaokanAndroid.R;
import com.example.taotaokanAndroid.WaresItems;
import com.example.taotaokanAndroid.imageCache.ImageLoader;

import java.util.ArrayList;



public class MainListGridAdapter extends BaseAdapter
{

    private LayoutInflater inflater;
    public ImageLoader imageLoader;
    private ArrayList<WaresItems> gridItemList  =null;
    public MainListGridAdapter(ArrayList<WaresItems> inputList, ImageLoader inputImageloader, Context context)
    {
        super();
        inflater = LayoutInflater.from(context);
        gridItemList = inputList;
        imageLoader = inputImageloader;
    }
    @Override
    public int getCount( )
    {
        if (null != gridItemList)
        {
            return gridItemList.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getItem( int position )
    {
        return gridItemList.get(position);
    }

    @Override
    public long getItemId( int position )
    {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.grid_item_mainlist, null);
            viewHolder = new ViewHolder();
            //viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.theme_detail_image);
            //viewHolder.time = (TextView) convertView.findViewById(R.id.description);

            viewHolder.priceView = (TextView)convertView.findViewById(R.id.theme_detail_price);
            viewHolder.sellAmount = (TextView)convertView.findViewById(R.id.theme_detail_sell_amount);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.title.setText(gridItemList.get(position).getTitle());
        //viewHolder.time.setText(gridItemList.get(position).getTime());
        WaresItems item = gridItemList.get(position);
        viewHolder.priceView.setText(""+item.itemPromotionPrice+"元");
        viewHolder.sellAmount.setText(""+item.tradingVolumeInThirtyDays+"件");
        String bigImageString = item.itemImageURLString.replace("240x240", "400x400");

        imageLoader.DisplayImage(bigImageString, viewHolder.image);


        return convertView;
    }
}