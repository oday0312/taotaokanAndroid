package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-11-23
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
public class GridViewActivity extends Activity {

    private GridView gridView;
    //图片的第一行文字
    private String[] titles = new String[]
            { "美女卷珠帘", "美女回眸", "美女很有趣", "美女醉酒", "美女微笑", "美女如脱兔", "美女柳叶弯眉"};
    //图片的第二行文字
    private String[] description = new String[]
            { "啦啦啦", "嘎嘎嘎", "哇哇哇", "喵喵喵", "刚刚刚", "当当当", "咔咔咔"};
    //图片ID数组
    private int[] images = {
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy,
            R.drawable.groupbuy };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        gridView = (GridView) findViewById(R.id.gridview);
        GridItemType1Adapter adapter = new GridItemType1Adapter(titles, images,description,this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(GridViewActivity.this, "item" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }
}