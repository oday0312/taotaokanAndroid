package com.example.taotaokanAndroid;

import android.app.Application;
import android.content.SharedPreferences;
import com.example.taotaokanAndroid.imageCache.ImageLoader;
import com.theindex.CuzyAdSDK.CuzyAdSDK;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-12-4
 * Time: PM4:57
 * To change this template use File | Settings | File Templates.
 */
public class TaoTaoMainApplication extends Application {

    public final String SHARE_FILE_NAME = "taotaokan";
    public final String BACKGROUND_KEY = "desktop background";
    public ImageLoader imageLoader=  null;


    public int[] images = {
            R.drawable.bg_source_0,
            R.drawable.bg_source_1,
            R.drawable.bg_source_2,
            R.drawable.bg_source_3,
            R.drawable.bg_source_4,
            R.drawable.bg_source_5,
            R.drawable.bg_source_6,
            R.drawable.bg_source_7,
            R.drawable.bg_source_8,
            R.drawable.bg_source_9,
            R.drawable.bg_source_10,
            R.drawable.bg_source_11,
            R.drawable.bg_source_12,
    };

    public String[] categoryThemeIDStrings = new String[]
            {
                    "3",
                    "4",
                    "2",
                    "209",
                    "210",

                    "206",
                    "202",
                    "208",
                    "207",

                    "214",
                    "215",
                    "212",
                    "201",


                    "1",
                    "213",
                    "203",

                    "216",
                    "205",
                    "217",
                    "211",

                    ///////
                    "",
                    "精美DIY",
                    "精选推荐",
                    "",





            };

    public String[] categoryStrings = new String[]{
            "19.9包邮",
            "29.9包邮",
            "9.9包邮",
            "母婴儿童",
            "精美女包",

            "美容护肤",
            "潮流女装",
            "时尚女鞋",
            "数码配件",

            "美食特产",
            "精美礼物",
            "家具建材",
            "个性男装",


            "最新商品",
            "户外运动",
            "精品男鞋",

            "女生裤袜",
            "运动健生",
            "女生上衣",
            "舒适内衣",
            ///////
            "",
            "精美DIY",
            "精选推荐",
            "",





    };
    public int[] categoryImages = {
            R.drawable.ca_19,
            R.drawable.ca_29,
            R.drawable.ca_9,
            R.drawable.ca_baby,
            R.drawable.ca_bag,

            R.drawable.ca_cosmetic,
            R.drawable.ca_fashion,
            R.drawable.ca_female_shoe,
            R.drawable.ca_figure,

            R.drawable.ca_food,
            R.drawable.ca_gift,
            R.drawable.ca_home,
            R.drawable.ca_male,

            R.drawable.ca_new,
            R.drawable.ca_outdoors,
            R.drawable.ca_shoe,

            R.drawable.ca_socks,
            R.drawable.ca_sport,
            R.drawable.ca_tops,
            R.drawable.ca_underskirt,


            /////
            R.drawable.ca_redwine,
            R.drawable.ca_diy,
            R.drawable.ca_select,
            R.drawable.ca_jewelry,




    };



    public int backgroundResourceID= R.drawable.bg_source_1;

    @Override
    public void onCreate() {
        super.onCreate();
        CuzyAdSDK.getInstance().setContext(this);
        CuzyAdSDK.getInstance().registerApp("200003","208f53acd6d396867c2a721be6c807eb");
        SharedPreferences sp = getSharedPreferences(SHARE_FILE_NAME, MODE_PRIVATE);
        String backString  = sp.getString(BACKGROUND_KEY,"");
        if (backString.length()>0)
        {
            backgroundResourceID = Integer.parseInt(backString);
        }

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
