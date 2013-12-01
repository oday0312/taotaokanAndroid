package com.example.taotaokanAndroid;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import com.example.taotaokanAndroid.SQLiteHelper.WaresItemsDataUtil;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-11-29
 * Time: AM10:29
 * To change this template use File | Settings | File Templates.
 */
public class FavorView extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorview);

        testSQLite();
    }



    public void testSQLite()
    {
        //插入
        WaresItemsDataUtil dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();
        dbUtil.createStudent("Prashant Thakkar",
                "10th",
                               "3", "4","5","6",
                                "7", "8", "9 "
                                );
        dbUtil.close();

        //查询
        dbUtil = new WaresItemsDataUtil(this);
        dbUtil.open();
        Cursor cursor = dbUtil.fetchAllStudents();
        if(cursor != null){
            while(cursor.moveToNext()){
            Log.i("Student", "Student Name: " + cursor.getString(1) +
                    " Grade " + cursor.getString(2));
            }
        }
        dbUtil.close();
    }
}
