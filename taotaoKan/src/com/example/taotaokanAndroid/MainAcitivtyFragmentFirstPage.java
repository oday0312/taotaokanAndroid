package com.example.taotaokanAndroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    public ArrayList<Integer>images = new ArrayList<Integer>();


    public int index = 0;
    public static MainAcitivtyFragmentFirstPage newInstance(String content, int inputIndex) {
        MainAcitivtyFragmentFirstPage fragment = new MainAcitivtyFragmentFirstPage();

        fragment.mContent = "";

        fragment.index = inputIndex;






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
        GalleryImageAdapter adapter = new GalleryImageAdapter(v.getContext(), application.wareItemsArray);
        myGallery.setAdapter(adapter);

        TypedArray typedArray = v.getContext().obtainStyledAttributes(R.styleable.Gallery);
        adapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));



        ///this is for GridView adpter..
        startInit();
        GridItemType1Adapter Gridadapter = new GridItemType1Adapter(titles, images,description,v.getContext());
        gridView.setAdapter(Gridadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //Toast.makeText(getActivity(), "item" + (position + 1), Toast.LENGTH_SHORT).show();
                startShowThemeItems(position);
            }
        });

        return v;


    }


    public void startShowThemeItems(int position)
    {
        Intent intent = new Intent(getActivity(), showThemeItemsActivity.class);
        intent.putExtra(EXTRA_Theme_String, description.get(position));

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        getActivity().overridePendingTransition(0, 0);
    }


    public void startInit()
    {
        int size= 6;
        titles.clear();
        description.clear();
        images.clear();

        TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();

        for (int i = 0;i<size;i++)
        {
            titles.add(application.categoryStrings[i]);
            description.add(application.categoryThemeIDStrings[i]);
            images.add(application.categoryImages[i]);
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver

    {

        public void onReceive(Context context, Intent intent)

        {

            String action = intent.getAction();



        }

    }
    public void onStart()
    {
        super.onStart();

    }


    public void onStop()
    {
        super.onStop();
    }

}
