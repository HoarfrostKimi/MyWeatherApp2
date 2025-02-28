package com.example.myweatherapp2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME = "mytable";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表的 SQL 语句
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, city TEXT UNIQUE, cityId INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {
            // 删除旧表并重新创建新表
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    // 插入数据的方法
    public long insertCity(String city, int cityId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int newId = getNextId(db);
        ContentValues values = new ContentValues();
        values.put("id", newId);
        values.put("city", city);
        values.put("cityId", cityId);
        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            reorderIds(db);
        }
        db.close();
        return newRowId;
    }

    public void deleteCityById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        reorderIds(db);
        db.close();
    }

    private void reorderIds(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id"}, null, null, null, null, "id ASC");
        int newId = 1;
        int idIndex = cursor.getColumnIndex("id");

        if (idIndex == -1) {
            throw new IllegalArgumentException("Column 'id' not found in the table.");
        }

        if (cursor.moveToFirst()) {
            do {
                int oldId = cursor.getInt(idIndex);
                if (oldId != newId) {
                    ContentValues values = new ContentValues();
                    values.put("id", newId);
                    String whereClause = "id = ?";
                    String[] whereArgs = {String.valueOf(oldId)};
                    db.update(TABLE_NAME, values, whereClause, whereArgs);
                }
                newId++;
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    // 获取下一个可用的 id
    private int getNextId(SQLiteDatabase db) {
        int nextId = 1;
        Cursor cursor = db.query(TABLE_NAME, new String[]{"MAX(id)"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int maxId = cursor.getInt(0);
            if (!cursor.isNull(0)) {
                nextId = maxId + 1;
            }
        }
        cursor.close();
        return nextId;
    }
}