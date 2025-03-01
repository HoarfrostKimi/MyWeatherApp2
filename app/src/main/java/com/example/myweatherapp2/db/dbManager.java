package com.example.myweatherapp2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class dbManager {
    public static SQLiteDatabase database;
    //初始化数据库信息
    public static void initDB(Context context){
        DBhelper dbHelper=new DBhelper(context);
        database=dbHelper.getWritableDatabase();
    }



}
