package com.example.taotaokanAndroid.gridView;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-2
 * Time: PM3:54
 * To change this template use File | Settings | File Templates.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taotaokanAndroid.R;

import java.util.ArrayList;
import java.util.List;

import com.example.taotaokanAndroid.WaresItems;
import com.example.taotaokanAndroid.imageCache.ImageLoader;

public class GridItemType2FavorAdapter extends BaseAdapter
{

    private LayoutInflater inflater;
    private List<WaresItems> gridItemList;
    public ImageLoader imageLoader=  null;

    public GridItemType2FavorAdapter(ArrayList<WaresItems> inputList,ImageLoader inputImageloader, Context context)
    {
        super();
        gridItemList = new ArrayList<WaresItems>();
        inflater = LayoutInflater.from(context);
        gridItemList.addAll(inputList);
        this.imageLoader = inputImageloader;
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
            convertView = inflater.inflate(R.layout.grid_item1, null);
            viewHolder = new ViewHolder();
            //viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            //viewHolder.time = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.title.setText(gridItemList.get(position).itemName);
        //viewHolder.time.setText(gridItemList.get(position).itemPrice);
        //final Bitmap temp = getRes("");
        imageLoader.DisplayImage(gridItemList.get(position).itemImageURLString , viewHolder.image);
        return convertView;
    }
}