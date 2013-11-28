package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.taotaokanAndroid.imageCache.ImageLoader;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-28
 * Time: PM5:37
 * To change this template use File | Settings | File Templates.
 */
public class DetailViewLevel1 extends Activity {

    public static final String EXTRA_PAPERITEM = "com.devspark.sidenavigation.meiriyiwen.extra.paperitem";
    public ImageLoader imageLoader;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailviewlevel1);


        imageLoader = new ImageLoader(this);
        if (getIntent().hasExtra(EXTRA_PAPERITEM))
        {
            PaperItem item = getIntent().getParcelableExtra(EXTRA_PAPERITEM);
            ImageView imageView = (ImageView)findViewById(R.id.detailviewlevel1_imageview);

            TextView description = (TextView)findViewById(R.id.detailviewlevel1_description);
            TextView postview = (TextView)findViewById(R.id.detailviewlevel1_postfree);
            TextView sourceType = (TextView)findViewById(R.id.detailviewlevel1_item_sourceType);
            TextView MonthlyAmount = (TextView)findViewById(R.id.detailviewlevel1_30day_sellamount);

            description.setText(item.titleString);
            imageLoader.DisplayImage(item.imageString, imageView);
        }



    }
}