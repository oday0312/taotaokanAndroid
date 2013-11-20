package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
// You can also assign the title programmatically by passing a
// CharSequence or resource id.
//actionBar.setTitle(R.string.some_title);
        actionBar.setHomeAction(new ActionBar.IntentAction(this, MainActivity.createIntent(this), R.drawable.ic_title_home_default));
        //actionBar.addAction(new ActionBar.IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default));
        actionBar.addAction(new ToastAction());
    }



    static public Intent createIntent(Context context)
    {
           Intent t = new Intent();


        return t;
    }

    private class ToastAction implements ActionBar.Action {

        @Override
        public int getDrawable() {
            return R.drawable.ic_title_export_default;
        }

        @Override
        public void performAction(View view) {

            Toast.makeText(getApplicationContext(),
                    "Example action", Toast.LENGTH_SHORT).show();
        }

    }
}
