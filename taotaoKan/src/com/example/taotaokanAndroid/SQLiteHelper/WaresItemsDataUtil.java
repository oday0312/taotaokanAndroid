package com.example.taotaokanAndroid.SQLiteHelper;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-12-1
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.taotaokanAndroid.WaresItems;

public class WaresItemsDataUtil {

    private static final String TAG = "DatabaseUtil";

    /**
     * Database Name
     */
    private static final String DATABASE_NAME = "Taotaokan_database";

    /**
     * Database Version
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Table Name
     */
    private static final String DATABASE_TABLE = "tb_waresItemsFavorite";

    /**
     * Table columns
     */

    public static final String KEY_ROWID = "kid";
    ///WE ARE USING THE IMAGE URL STRING AS THE KEY..
    public static final String KEY_itemImageURLString = "itemImageURLString";
    public static final String KEY_itemName = "itemName";
    public static final String KEY_itemPrice = "itemPrice";

    public static final String KEY_itemPromotionPrice = "itemPromotionPrice";
    public static final String KEY_itemClickURLString = "itemClickURLString";
    public static final String KEY_tradingVolumeInThirtyDays = "tradingVolumeInThirtyDays";

    public static final String KEY_itemDescription = "itemDescription";
    public static final String KEY_itemType = "itemType";
    public static final String KEY_itemFreePostage = "itemFreePostage";
    /**
     * Database creation sql statement
     */
    private static final String CREATE_STUDENT_TABLE =
            "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_itemImageURLString +" text key not null, "
                    + KEY_itemName +" text not null, "
                    + KEY_itemPrice +" text not null, "
                    + KEY_itemPromotionPrice +" text not null, "
                    + KEY_itemClickURLString +" text not null, "
                    + KEY_tradingVolumeInThirtyDays +" text not null, "
                    + KEY_itemType +" text not null, "
                    + KEY_itemDescription +" text not null, "
                    + KEY_itemFreePostage + " text not null);";

    /**
     * Context
     */
    private final Context mCtx;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Inner private class. Database Helper class for creating and updating database.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        /**
         * onCreate method is called for the 1st time when database doesn't exists.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "Creating DataBase: " + CREATE_STUDENT_TABLE);
            db.execSQL(CREATE_STUDENT_TABLE);
        }
        /**
         * onUpgrade method is called when database version changes.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public WaresItemsDataUtil(Context ctx) {
        this.mCtx = ctx;
    }
    /**
     * This method is used for creating/opening connection
     * @return instance of DatabaseUtil
     * @throws SQLException
     */
    public WaresItemsDataUtil open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    /**
     * This method is used for closing the connection.
     */
    public void close() {
        mDbHelper.close();
    }

    /**
     * This method is used to create/insert new record Student record.
     * @return long
     */
    public long createStudent(WaresItems waresItems ) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_itemClickURLString,waresItems.itemClickURLString);
        initialValues.put(KEY_itemImageURLString,waresItems.itemImageURLString);
        initialValues.put(KEY_itemDescription,waresItems.itemDescription);

        initialValues.put(KEY_itemFreePostage,waresItems.itemFreePostage);
        initialValues.put(KEY_itemName,waresItems.itemName);
        initialValues.put(KEY_itemPrice,waresItems.itemPrice);

        initialValues.put(KEY_itemPromotionPrice,waresItems.itemPromotionPrice);
        initialValues.put(KEY_itemType,waresItems.itemType);
        initialValues.put(KEY_tradingVolumeInThirtyDays,waresItems.tradingVolumeInThirtyDays);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    /**
     * This method will delete Student record.
     * @return boolean
     */
    public boolean deleteStudent(String ImageUrlString) {
        return mDb.delete(DATABASE_TABLE, KEY_itemImageURLString + "=" + ImageUrlString, null) > 0;
    }

    /**
     * This method will return Cursor holding all the Student records.
     * @return Cursor
     */
    public Cursor fetchAllStudents() {
        return mDb.query(DATABASE_TABLE, new String[] {
                KEY_ROWID,
                KEY_itemClickURLString,
                KEY_itemDescription,
                KEY_itemFreePostage,
                KEY_itemImageURLString,
                KEY_itemName,
                KEY_itemPrice,
                KEY_itemPromotionPrice,
                KEY_itemType,
                KEY_tradingVolumeInThirtyDays}, null, null, null, null, null);
    }

    /**
     * This method will return Cursor holding the specific Student record.
     * @return Cursor
     * @throws SQLException
     */
    public Cursor fetchStudent(String imageURLstring) throws SQLException {
        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]
                                {KEY_ROWID,
                                KEY_itemClickURLString,
                                KEY_itemDescription,
                                KEY_itemFreePostage,
                                KEY_itemImageURLString,
                                KEY_itemName,
                                KEY_itemPrice,
                                KEY_itemPromotionPrice,
                                KEY_itemType,
                                KEY_tradingVolumeInThirtyDays}, KEY_itemImageURLString + "=" + imageURLstring, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * This method will update Student record.
     * @param id
     * @param name
     * @param standard
     * @return boolean
     */
    public boolean updateStudent(int id, String name, String standard) {
        //ContentValues args = new ContentValues();
        //return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + id, null) > 0;
        //todo: currently not need for update..
        return false;
    }
}




////插入
//DatabaseUtil dbUtil = new DatabaseUtil(this);
//dbUtil.open();
//dbUtil.createStudent("Prashant Thakkar", "10th");
//dbUtil.close();
//
////查询
//DatabaseUtil dbUtil = new DatabaseUtil(this);
//dbUtil.open();
//Cursor cursor = dbUtil.fetchAllStudents();
//if(cursor != null){
//        while(cursor.moveToNext()){
//        Log.i("Student", "Student Name: " + cursor.getString(1) +
//        " Grade " + cursor.getString(2));
//}
//        }
//        dbUtil.close();

