package com.example.taotaokanAndroid;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 14-1-4
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */

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
public class MainActivityFragment_list extends MainAcitivtyFragment {
    public static final String EXTRA_PAPERITEM = "com.devspark.sidenavigation.meiriyiwen.extra.paperitem";


    //图片的第一行文字
    public ArrayList<String> titles = new ArrayList<String>();
    //图片的第二行文字
    public ArrayList<String> description = new ArrayList<String>();
    //图片ID数组
    public ArrayList<Integer>images = new ArrayList<Integer>();


    public int index = 0;
    public GalleryImageAdapter simpleWindowADadapter;
    public MainActivityFragment_list()
    {
        super();

    }
    public static MainActivityFragment_list newInstance(String content, int inputIndex) {
        MainActivityFragment_list fragment = new MainActivityFragment_list();

        fragment.mContent = "";

        fragment.index = inputIndex;



        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = viewCreator2(inflater, container,savedInstanceState);
        return v;

    }

    public View viewCreator2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.mainframe_list, container, false);


        final TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();
        Drawable d =getResources().getDrawable(application.backgroundResourceID );
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


        simpleWindowADadapter = new GalleryImageAdapter(v.getContext(), application.wareItemsArray);
        simpleWindowADadapter.GalleryImageSize = application.screenWidth - 10;

        TypedArray typedArray = v.getContext().obtainStyledAttributes(R.styleable.Gallery);
        simpleWindowADadapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));



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


    public void startDetailViewLevel1(WaresItems item)
    {
        Intent intent = new Intent(this.getActivity(), DetailViewLevel1.class);
        intent.putExtra(EXTRA_PAPERITEM, item);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        getActivity().overridePendingTransition(0, 0);
    }


    public void startBigWindows(int position)
    {

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





    public void onStart()
    {
        super.onStart();

    }


    public void onStop()
    {
        super.onStop();
    }

}
