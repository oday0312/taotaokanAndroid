package com.example.taotaokanAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

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

    private ListView classItemList;
    private ClassListAdapter classListAdapter;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.setting);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);



        classItemList = (ListView) findViewById(R.id.classItemListView);
        classListAdapter = new ClassListAdapter(this);
        classItemList.setAdapter(classListAdapter);

        Vector<ClassItem> data = new Vector<ClassItem>();

        /**
         * ���������  �����ʵ��ݿ������Ҹ����xml���
         */
        ClassItem item1 = new ClassItem(1,"ɢ��������",1,"��ȷ��","");
        ClassItem item2 = new ClassItem(1,"ɢ��������",2,"δȷ��","");
        ClassItem item3 = new ClassItem(1,"ɢ��������",2,"δȷ��","");
        ClassItem item4 = new ClassItem(1,"ɢ��������",3,"��ȡ��","");

        data.addElement(item1);
        data.addElement(item2);
        data.addElement(item3);
        data.addElement(item4);

        addAdapterItem(data);



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
    public void onBackPressed() {
       finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

}


