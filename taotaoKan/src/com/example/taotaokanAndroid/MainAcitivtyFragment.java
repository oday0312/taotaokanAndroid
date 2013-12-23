package com.example.taotaokanAndroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import com.example.taotaokanAndroid.PullToRefresh.PullToRefreshView;
import com.example.taotaokanAndroid.gridView.GridItemType1Adapter;

import java.util.ArrayList;

public class MainAcitivtyFragment extends Fragment {

    public static final String KEY_CONTENT = "MainAcitivtyFragment:Content";
    public static final String EXTRA_Theme_String = "THEME_STRING";


    public GridView gridView;
    //图片的第一行文字
    public ArrayList<String> titles = new ArrayList<String>();
    //图片的第二行文字
    public ArrayList<String> description = new ArrayList<String>();
    //图片ID数组
    public ArrayList<Integer>images = new ArrayList<Integer>();



    public int index = 0;

    public static MainAcitivtyFragment newInstance(String content, int inputIndex) {
        MainAcitivtyFragment fragment = new MainAcitivtyFragment();
        fragment.index = inputIndex;
        fragment.mContent = "";



        return fragment;
    }

    public String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
        startInit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //View v = viewCreator1();
        View v = viewCreator2(inflater, container,savedInstanceState);


        return v;

    }

    public View viewCreator2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v =  inflater.inflate(R.layout.gridview, container, false);
        gridView = (GridView)v.findViewById(R.id.gridview);

        TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();
        Drawable d =getResources().getDrawable(application.backgroundResourceID );
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(d);
        } else {
            v.setBackground(d);
        }


        startInit();
        GridItemType1Adapter adapter = new GridItemType1Adapter(titles, images,description,v.getContext());
        gridView.setAdapter(adapter);

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

    public View viewCreator1()
    {
        TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(text);

        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

    public void startInit()
    {
        int startIndex = 0;
        int endIndex = 0;

        if (index == 1)
        {
            startIndex = 6;
            endIndex = 6+ 15;
        }
        else if (index == 2)
        {
            startIndex = 21;
            endIndex = 21+ 15;
        }

        titles.clear();
        description.clear();
        images.clear();

        TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();

        for (int i = startIndex ;i< endIndex && i<application.categoryImages.length-4;i++)
        {
            titles.add(application.categoryStrings[i]);
            description.add(application.categoryThemeIDStrings[i]);
            images.add( application.categoryImages[i]);
        }
    }

}


//mPullToRefreshView = (PullToRefreshView)v.findViewById(R.id.main_pull_refresh_view);
//mPullToRefreshView.setOnHeaderRefreshListener(this);
//mPullToRefreshView.setOnFooterRefreshListener(this);
