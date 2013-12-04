package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.gridView.GalleryImageAdapter;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;
import android.content.res.TypedArray;
/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-11-30
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class TestFragmentFirstPage extends TestFragment {
    public Gallery myGallery;


    //图片的第一行文字
    public String[] titles = new String[]
            { "第一", "1", "1", "1", "1", "美女如脱兔", "美女柳叶弯眉"};
    //图片的第二行文字
    public String[] description = new String[]
            { "啦啦啦", "嘎嘎嘎", "哇哇哇", "喵喵喵", "刚刚刚", "当当当", "咔咔咔"};
    //图片ID数组
    public int[] images = {
            R.drawable.ca_custom0,
            R.drawable.ca_custom1,
            R.drawable.ca_custom10,
            R.drawable.ca_custom11,
            R.drawable.ca_custom12,
            R.drawable.ca_custom13,
            R.drawable.ca_custom14 };

    public static TestFragmentFirstPage newInstance(String content) {
        TestFragmentFirstPage fragment = new TestFragmentFirstPage();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //View v = viewCreator1();
        View v = viewCreator2(inflater, container,savedInstanceState);


        return v;

    }

    public View viewCreator2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.gridview_firstpage, container, false);
        gridView = (GridView)v.findViewById(R.id.gridview_firstpage);



        myGallery = (Gallery)v.findViewById(R.id.gallery);
        //这段代码是杨丰盛老师的《android开发揭秘》中这样写的
        //myGallery.setBackgroundResource(R.drawable.bg0);
        GalleryImageAdapter adapter = new GalleryImageAdapter(v.getContext());
        //设置背景风格。Gallery背景风格定义在attrs.xml中
        TypedArray typedArray = v.getContext().obtainStyledAttributes(R.styleable.Gallery);
        adapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
        myGallery.setAdapter(adapter);



        mPullToRefreshView = (PullToRefreshView)v.findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        GridItemType1Adapter Gridadapter = new GridItemType1Adapter(titles, images,description,v.getContext());
        gridView.setAdapter(Gridadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // Toast.makeText(GridViewActivity.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

        return v;


    }
}