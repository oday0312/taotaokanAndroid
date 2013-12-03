package com.example.taotaokanAndroid;

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

public class TestFragment extends Fragment implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener {

    public static final String KEY_CONTENT = "TestFragment:Content";

    PullToRefreshView mPullToRefreshView;
    public GridView gridView;
    //图片的第一行文字
    public String[] titles = new String[]
            { "美女卷珠帘", "美女回眸", "美女很有趣", "美女醉酒", "美女微笑", "美女如脱兔", "美女柳叶弯眉"};
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




    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    public String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
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

        mPullToRefreshView = (PullToRefreshView)v.findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);

        GridItemType1Adapter adapter = new GridItemType1Adapter(titles, images,description,v.getContext());
        gridView.setAdapter(adapter);

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

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {

                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1000);
    }
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 璁剧疆鏇存柊鏃堕棿
                //mPullToRefreshView.onHeaderRefreshComplete("鏈�繎鏇存柊:01-23 12:01");
                mPullToRefreshView.onHeaderRefreshComplete();
            }
        },1000);

    }
}
