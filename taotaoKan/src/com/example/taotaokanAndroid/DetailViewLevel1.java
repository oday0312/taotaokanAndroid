package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
            WaresItems item = getIntent().getParcelableExtra(EXTRA_PAPERITEM);
            ImageView imageView = (ImageView)findViewById(R.id.detailviewlevel1_imageview);

            TextView description = (TextView)findViewById(R.id.detailviewlevel1_description);
            TextView postview = (TextView)findViewById(R.id.detailviewlevel1_postfree);
            TextView sourceType = (TextView)findViewById(R.id.detailviewlevel1_item_sourceType);
            TextView MonthlyAmount = (TextView)findViewById(R.id.detailviewlevel1_30day_sellamount);

            description.setText(item.titleString);


            //http:\/\/img01.taobaocdn.com\/bao\/uploaded\/i1\/16244030445984444\/T1KDESXfdbXXXXXXXX_!!0-item_pic.jpg_240x240.jpg"
            String bigImageString = item.imageString.replace("240x240", "400x400");
            Log.d("alex huang" , bigImageString);
            imageLoader.DisplayImage(bigImageString, imageView);
        }



    }
}