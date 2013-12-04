package com.example.taotaokanAndroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taotaokanAndroid.ClassItem.ClassItem;
import com.example.taotaokanAndroid.ClassItem.ClassListAdapter;
import com.example.taotaokanAndroid.imageCache.FileCache;
import com.example.taotaokanAndroid.imageCache.ImageLoader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.ConversationActivity;
import com.umeng.fb.FeedbackAgent;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-21
 * Time: PM3:16
 * To change this template use File | Settings | File Templates.
 */
public class SettingActivity extends Activity {
    public static final String EXTRA_WEBURL = "com.devspark.sidenavigation.meiriyiwen.extra.weburl";
    public static final String EXTRA_TITLE_SHOW = "com.devspark.sidenavigation.meiriyiwen.extra.title.show";
    public static final String EXTRA_TITLE_TEXT = "com.devspark.sidenavigation.meiriyiwen.extra.title.text";



    private ListView classItemList;
    private ClassListAdapter classListAdapter;

    private String faqURLstring = "http://iminitao.com/app/fq.html";

    public Vector<ClassItem> data;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);


        TextView tv = (TextView)findViewById(R.id.titleText);
        tv.setText("设置");

        classItemList = (ListView) findViewById(R.id.classItemListView);
        classListAdapter = new ClassListAdapter(this);
        classItemList.setAdapter(classListAdapter);

        data = new Vector<ClassItem>();
        createData();


        // Click on ListView Row:
        classItemList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
               //
                switch (position)
                {
                    case 1:
                        clearCacheFile();
                        break;
                    case 2:
                        startWebViewActivity(faqURLstring);
                        break;
                    case 3:
                        startSetDesktopBackground();
                        break;

                    ///////////////////////////////
                    case 4:
                        startVersionCheck();
                        break;
                    case 5:
                        startFeedBackUMENG();
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }


            }
        });

    }

    public void startSetDesktopBackground()
    {
        Intent intent = new Intent(this, SettingSelectingBG.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void startVersionCheck()
    {
        Toast.makeText(this, "当前已经是最新版本", Toast.LENGTH_SHORT).show();
    }
    public void createData()
    {
        data.clear();
        String versionName = "";
        try
        {
            PackageInfo pinfo;
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionNumber = pinfo.versionCode;
            versionName = pinfo.versionName;
        }
        catch (Exception e)
        {

        }


        FileCache cache = new FileCache(this);
        ClassItem item0 = new ClassItem(ClassItem.classType.SETTING_LOGO,versionName );
        ClassItem item1 = new ClassItem(1,"清理缓存文件 " + cache.getSize(),1,"程序","");
        ClassItem item2 = new ClassItem(1,"帮助说明",1,"程序","");
        ClassItem item3 = new ClassItem(1,"设置桌面背景",1,"程序","");
        //ClassItem item4 = new ClassItem(1,"给我们打分",2,"其他","");
        ClassItem item5 = new ClassItem(1,"版本更新",2,"其他","");
        ClassItem item6 = new ClassItem(1,"反馈和建议",2,"其他","");


        data.addElement(item0);
        data.addElement(item1);
        data.addElement(item2);
        data.addElement(item3);
        //data.addElement(item4);
        data.addElement(item5);
        data.addElement(item6);

        addAdapterItem(data);
    }

    public void clearCacheFile()
    {
        FileCache cache = new FileCache(this);
        cache.clear();
        Toast.makeText(this, "缓存已经清除", Toast.LENGTH_SHORT).show();
        createData();
        classListAdapter.notifyDataSetChanged();


    }
    public void startFeedBackUMENG()
    {
        Intent intent = new Intent(this, ConversationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void addAdapterItem(Vector<ClassItem> data){
        Vector<ClassItem> classItem = new Vector<ClassItem>();
        classItem.removeAllElements();

        ClassItem temp = null;
        Set<Integer> set = new HashSet<Integer>();
        if(data!=null && data.size()>0){
            for(int i=0 ; i<data.size() ; i++){
                temp = data.get(i);// ��ȡ���
                if(set.contains(temp.partId)){// �ж��Ƿ�������partid ������ ˵�������������ͬ�����Ŀ��
                    classItem.add(temp);
                }else{
                    temp.ifTop = true;//  �����ö�  Ҳ������ʾ��Ŀ
                    set.add(temp.partId);// ������partid ��ӵ�set �Ա�����ж�
                    classItem.add(temp);
                }
            }
            classListAdapter.removeAll();
            for(ClassItem item : classItem){
                classListAdapter.addItem(item);
            }
        }
    }


    public void startWebViewActivity(String urlString)
    {
        Intent intent = new Intent(this, webViewActivity.class);
        intent.putExtra(EXTRA_WEBURL, urlString);
        intent.putExtra(EXTRA_TITLE_SHOW,"true");
        intent.putExtra(EXTRA_TITLE_TEXT, "帮助");
        // all of the other activities on top of it will be closed and this
        // Intent will be delivered to the (now on top) old activity as a
        // new Intent.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // no animation of transition
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
       finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}


