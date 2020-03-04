package com.bawei.bwonlineshopping.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "serach.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table shop(name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
