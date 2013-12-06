package com.example.taotaokanAndroid.gridView;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-11-23
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taotaokanAndroid.R;

public class GridItemType1Adapter extends BaseAdapter
{

    private LayoutInflater inflater;
    private List<gridItemType1> gridItemList;

    public GridItemType1Adapter(ArrayList<String> titles, ArrayList<Integer> images,ArrayList<String> description, Context context)
    {
        super();
        gridItemList = new ArrayList<gridItemType1>();
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < images.size(); i++)
        {
            gridItemType1 picture = new gridItemType1(titles.get(i), images.get(i),description.get(i));
            gridItemList.add(picture);
        }
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
        //viewHolder.title.setText(gridItemList.get(position).getTitle());
        //viewHolder.time.setText(gridItemList.get(position).getTime());
        viewHolder.image.setImageResource(gridItemList.get(position).getImageId());
        return convertView;
    }
}