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
    //查询数据库中的城市信息
    public static List<String> queryAllCityName() {
        Cursor cursor = database.query("mytable", null, null, null, null, null, null);
        List<String> cityList = new ArrayList<>();
        if (cursor != null) {
            int cityColumnIndex = cursor.getColumnIndex("city");
            if (cityColumnIndex != -1) {
                while (cursor.moveToNext()) {
                    String city = cursor.getString(cityColumnIndex);
                    cityList.add(city);
                }
            }
            cursor.close();
        }
        return cityList;
    }
    //根据城市数据,替换信息内容
    public static int updateInfoByCity(String city,String content){
        ContentValues values=new ContentValues();
        values.put("content",content);
        return database.update("mytable",values,"city=?",new String[]{city});
    }
    //新增一条城市记录
    public static long addCityInfo(String city, String content) {
        ContentValues values = new ContentValues();
        values.put("city", city);
        values.put("cityId", content); // 这里假设content实际上是cityId的字符串形式
        return database.insert("mytable", null, values);
    }

    //根据城市名,查询数据库中的内容
    public static String queryInfoByCity(String city) {
        Cursor cursor = database.query("mytable", null, "city=?", new String[]{city}, null, null, null);
        String content = null; // 初始化 content 变量
        if (cursor != null && cursor.getCount() > 0) {
            try {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex("content");
                if (columnIndex != -1) {
                    content = cursor.getString(columnIndex);
                } else {
                    content = "列 'content' 不存在";
                }
            } finally {
                cursor.close();
            }
        }
        return content == null ? "无内容" : content;
    }
    public static  int getCityCount(){
        Cursor cursor=database.query("mytable",null,null,null,null,null,null);
        int count=cursor.getCount();
        return count;

    }
    public static boolean isCityExists(String city) {
        Cursor cursor = database.query("mytable", null, "city=?", new String[]{city}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

}
