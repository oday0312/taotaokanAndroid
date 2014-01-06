package com.example.taotaokanAndroid.gridView;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-11-23
 * Time: 下午9:44
 * To change this template use File | Settings | File Templates.
 */
class gridItemType1
{
    private String title;
    private int imageId;
    private String description;

    public gridItemType1()
    {
        super();
    }

    public gridItemType1(String title, int imageId,String time)
    {
        super();
        this.title = title;
        this.imageId = imageId;
        this.description = time;
    }

    public String getTime( )
    {
        return description;
    }

    public String getTitle()
    {
        return title;
    }

    public int getImageId()
    {
        return imageId;
    }
}

class ViewHolder
{
    public ImageView image;
    public TextView title;
    public TextView time;


    public TextView priceView;
    public TextView sellAmount;
    public TextView itemDescription;

    public TextView originalPriceView;
}