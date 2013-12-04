/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.taotaokanAndroid.Coverflow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taotaokanAndroid.R;
import com.example.taotaokanAndroid.TaoTaoMainApplication;

public class SimpleExample extends Activity {

    // =============================================================================
    // Child views
    // =============================================================================

    private FancyCoverFlow fancyCoverFlow;

    private FancyCoverFlowAdapter adapter;
    // =============================================================================
    // Supertype overrides
    // =============================================================================

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_desktopbg);


        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("设置桌面背景");

        this.fancyCoverFlow = (FancyCoverFlow) this.findViewById(R.id.fancyCoverFlow);

        adapter = new FancyCoverFlowSampleAdapter();
        this.fancyCoverFlow.setAdapter(adapter);
        this.fancyCoverFlow.setUnselectedAlpha(1.0f);
        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
        this.fancyCoverFlow.setUnselectedScale(0.5f);
        this.fancyCoverFlow.setSpacing(50);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.2f);
        this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);


       fancyCoverFlow.setOnItemClickListener(  new AdapterView.OnItemClickListener()
       {
           public void onItemClick(AdapterView adapterView, View view,int arg2, long arg3)
           {
               int selectedPosition = adapterView.getSelectedItemPosition();
               Toast.makeText(SimpleExample.this, "选择图片"+selectedPosition, Toast.LENGTH_SHORT).show();


               Integer ResourceID  = (Integer)adapter.getItem(selectedPosition);
               TaoTaoMainApplication application = (TaoTaoMainApplication)getApplication();
               application.backgroundResourceID = ResourceID;
               SharedPreferences sp = getSharedPreferences(application.SHARE_FILE_NAME, MODE_PRIVATE);
               SharedPreferences.Editor editor = sp.edit();
               editor.putString(application.BACKGROUND_KEY, ""+ResourceID);
               editor.commit();

               onBackPressed();
           }
       } );
    }

    // =============================================================================
    // Private classes
    // =============================================================================



}
