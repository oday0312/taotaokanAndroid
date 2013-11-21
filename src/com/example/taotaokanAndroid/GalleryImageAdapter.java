package com.example.taotaokanAndroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-21
 * Time: PM2:39
 * To change this template use File | Settings | File Templates.
 */
public class GalleryImageAdapter extends BaseAdapter  {
    // 用来设置ImageView的风格
    int mGalleryItemBackground;
    private Context context;
    //图片的资源ID
    private Integer[] mImageIds = {
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy
    };
    //构造函数
    public GalleryImageAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    //返回所有图片的个数
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mImageIds.length;
    }
    //返回图片在资源的位置
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    //返回图片在资源的位置
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    //此方法是最主要的，他设置好的ImageView对象返回给Gallery
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageView imageView = new ImageView(context);
        //通过索引获得图片并设置给ImageView
        imageView.setImageResource(mImageIds[position]);
        //设置ImageView的伸缩规格，用了自带的属性值
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //设置布局参数
        imageView.setLayoutParams(new Gallery.LayoutParams(480, 480));
        //设置风格，此风格的配置是在xml中
        imageView.setBackgroundResource(mGalleryItemBackground);
        return imageView;
    }

    public int getmGalleryItemBackground() {
        return mGalleryItemBackground;
    }

    public void setmGalleryItemBackground(int mGalleryItemBackground) {
        this.mGalleryItemBackground = mGalleryItemBackground;
    }

}
