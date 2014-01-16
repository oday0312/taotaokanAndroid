package com.theindex.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.theindex.taotaokanAndroid.gridView.GridItemType1Adapter;
import com.theindex.taotaokanAndroid.zxing.view.ViewfinderView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 14-1-7
 * Time: PM5:32
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityFragment_toolbar extends Fragment {

    public static final String KEY_CONTENT = "MainAcitivtyFragment_toolbar";
    public static final String EXTRA_Theme_String = "THEME_STRING";



    private OnMyButton1ClickListener mListener;
    public interface OnMyButton1ClickListener {
        public void onMyButton1Click(String themeString);
    }


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMyButton1ClickListener) activity;//这句就是赋初值了。
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnbtnSendClickListener");//这条表示，你不在Activity里实现这个接口的话，我就要抛出异常咯。知道下一步该干嘛了吧？
        }
    }
    public static MainActivityFragment_toolbar newInstance() {
        MainActivityFragment_toolbar fragment = new MainActivityFragment_toolbar();


        return fragment;
    }

    public String mContent = "abc";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = viewCreator2(inflater, container,savedInstanceState);


        return v;

    }

    public View viewCreator2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final TaoTaoMainApplication application = (TaoTaoMainApplication)getActivity().getApplication();
        View v =  inflater.inflate(R.layout.main_activity_toolbar, container, false);
        v.setVerticalFadingEdgeEnabled(false);
        Button bt1 = (Button)v.findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onMyButton1Click("1");
            }
        });
        int buttonWidth = application.screenWidth/4 ;
        int buttonHeight = buttonWidth / 2;
        bt1.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth,buttonHeight));

        Button bt2 = (Button)v.findViewById(R.id.button2);
        bt2.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth,buttonHeight));
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMyButton1Click("2");
            }
        });

        Button bt3 = (Button)v.findViewById(R.id.button3);
        bt3.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth,buttonHeight));
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMyButton1Click("3");
            }
        });

        Button bt4 = (Button)v.findViewById(R.id.button4);
        bt4.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth,buttonHeight));
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMyButton1Click("4");
            }
        });

        bt1.setText("值得买");
        bt2.setText("9.9包邮");
        bt3.setText("19.9包邮");
        bt4.setText("29.9包邮");



        return v;

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }




}
