package com.example.taotaokanAndroid;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.gridView.GalleryImageAdapter;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-11-30
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class MainAcitivtyFragmentFirstPage extends MainAcitivtyFragment {
    public Gallery myGallery;


    //图片的第一行文字
    public ArrayList<String> titles = new ArrayList<String>();
    //图片的第二行文字
    public ArrayList<String> description = new ArrayList<String>();
    //图片ID数组
    public int[] images = {

            R.drawable.ca_female_shoe,
            R.drawable.ca_baby,
            R.drawable.ca_9,
            R.drawable.ca_sport,
            R.drawable.ca_cosmetic,
            R.drawable.ca_29 };

    public static MainAcitivtyFragmentFirstPage newInstance(String content) {
        MainAcitivtyFragmentFirstPage fragment = new MainAcitivtyFragmentFirstPage();

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


        TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();
        Drawable d =getResources().getDrawable(application.backgroundResourceID );
        v.setBackground(d);

        myGallery = (Gallery)v.findViewById(R.id.gallery);
        //这段代码是杨丰盛老师的《android开发揭秘》中这样写的
        //myGallery.setBackgroundResource(R.drawable.bg0);
        GalleryImageAdapter adapter = new GalleryImageAdapter(v.getContext());
        //设置背景风格。Gallery背景风格定义在attrs.xml中
        TypedArray typedArray = v.getContext().obtainStyledAttributes(R.styleable.Gallery);
        adapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
        myGallery.setAdapter(adapter);



        startInit();
        GridItemType1Adapter Gridadapter = new GridItemType1Adapter(titles, images,description,v.getContext());
        gridView.setAdapter(Gridadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //Toast.makeText(getActivity(), "item" + (position + 1), Toast.LENGTH_SHORT).show();
                startShowThemeItems();
            }
        });

        return v;


    }


    public void startShowThemeItems()
    {
        Intent intent = new Intent(getActivity(), showThemeItemsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        getActivity().overridePendingTransition(0, 0);
    }


    public void startInit()
    {
        int size= 6;
        TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();

        for (int i = 0;i<size;i++)
        {
            titles.add(application.categoryStrings[i]);
            description.add(application.categoryThemeIDStrings[i]);
            this.images[i] = application.categoryImages[i];
        }
    }
}