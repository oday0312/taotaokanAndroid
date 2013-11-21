package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */


    private Gallery myGallery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
// You can also assign the title programmatically by passing a
// CharSequence or resource id.
//actionBar.setTitle(R.string.some_title);
        actionBar.setHomeAction(new ActionBar.IntentAction(this, createIntent(this), R.drawable.ic_title_home_default));
        //actionBar.addAction(new ActionBar.IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default));
        actionBar.addAction(new ToastAction());

        myGallery = (Gallery)findViewById(R.id.gallery);
        //这段代码是杨丰盛老师的《android开发揭秘》中这样写的
        //myGallery.setBackgroundResource(R.drawable.bg0);
        GalleryImageAdapter adapter = new GalleryImageAdapter(this);
        //设置背景风格。Gallery背景风格定义在attrs.xml中
        TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
        adapter.setmGalleryItemBackground(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
        myGallery.setAdapter(adapter);
    }


    public Intent createIntent(Context context)
    {
        Intent t = new Intent();
        t.setClass(MainActivity.this,SettingActivity.class);
        return t;
    }

    private class ToastAction implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.ic_title_export_default;
        }

        @Override
        public void performAction(View view) {

            Intent t = new Intent();
            t.setClass(MainActivity.this,SettingActivity.class);
            startActivity(t);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


            //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);


        }

    }
}
