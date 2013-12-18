package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taotaokanAndroid.SQLiteHelper.WaresItemsDataUtil;
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
    public WaresItemsDataUtil dbUtil;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailviewlevel1);


        dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();


        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("商品详情");
        final TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();


        imageLoader = new ImageLoader(this);
        if (getIntent().hasExtra(EXTRA_PAPERITEM))
        {
            final WaresItems item = getIntent().getParcelableExtra(EXTRA_PAPERITEM);
            ImageView imageView = (ImageView)findViewById(R.id.detailviewlevel1_imageview);
            ViewGroup.LayoutParams ps = imageView.getLayoutParams();
            ps.width = application.screenWidth -10;
            ps.height = application.screenWidth -10;
            imageView.setLayoutParams(ps);


            TextView description = (TextView)findViewById(R.id.detailviewlevel1_description);
            TextView postview = (TextView)findViewById(R.id.detailviewlevel1_postfree);
            TextView sourceType = (TextView)findViewById(R.id.detailviewlevel1_item_sourceType);
            TextView MonthlyAmount = (TextView)findViewById(R.id.detailviewlevel1_30day_sellamount);

            description.setText(item.itemName);
            final Context currentContent = this;

            TextView addToFavor = (TextView)findViewById(R.id.detailviewlevel1_item_add_favor_list);
            addToFavor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbUtil.createStudent(item );
                    Toast.makeText(currentContent, "添加收藏成功", Toast.LENGTH_SHORT).show();
                }
            });


            //http:\/\/img01.taobaocdn.com\/bao\/uploaded\/i1\/16244030445984444\/T1KDESXfdbXXXXXXXX_!!0-item_pic.jpg_240x240.jpg"
            String bigImageString = item.itemImageURLString.replace("240x240", "600x600");
            Log.d("alex huang" , bigImageString);
            imageLoader.DisplayImage(bigImageString, imageView);
        }



    }



    public void onBackPressed() {
        dbUtil.close();
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}