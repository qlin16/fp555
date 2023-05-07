package com.gdou.movieshop.databases;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author lqf
 * @Title:
 * @Package
 * @Description:
 * @date 2023/4/228:31 AM
 */

public class UserHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "id";
    public static final String COL_2 = "account";
    public static final String COL_3 = "name";
    public static final String COL_4 = "mobile";
    public static final String COL_5 = "status";
    public static final String COL_6 = "passwd";


    private static UserHelper mHelper = null;




    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("UserHelper","create table");
        db.execSQL("create table " + TABLE_NAME + " " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account TEXT," +
                "name TEXT," +
                "mobile TEXT," +
                "status INTEGER," +
                "passwd TEXT)");
    }

    //Obtain a unique instance of a database helper using a single column pattern
    public static UserHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new UserHelper(context);
        }
        return mHelper;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String account, String name,String mobile,Integer status,String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, account);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, mobile);
        contentValues.put(COL_5, status);
        contentValues.put(COL_6, passwd);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public Cursor query(String where) {
        System.out.println("select * from " + TABLE_NAME+" where 1=1 and "+where);
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where 1=1 and "+where, null);

        return res;
    }

    public boolean updateData(String id, String account, String name,String mobile,Integer status,String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, account);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, mobile);
        contentValues.put(COL_5, status);
        contentValues.put(COL_6, passwd);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{id});
    }
}

