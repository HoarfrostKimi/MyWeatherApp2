package com.example.myweatherapp2.CityList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.CityList.CityChoiceAdapter.CityChoiceAdapter;
import com.example.myweatherapp2.CityList.CityChoiceAdapter.CityChoiceModel;
import com.example.myweatherapp2.CityList.CityChoiceAdapter.SimpleItemTouchHelperCallback;
import com.example.myweatherapp2.Internet.NetUtil;
import com.example.myweatherapp2.MainActivity.MainActivity;
import com.example.myweatherapp2.MainActivity.WeatherBeanNow;
import com.example.myweatherapp2.R;
import com.example.myweatherapp2.SearchActivity.SearchActivity;
import com.example.myweatherapp2.db.DBhelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_CityChoice extends Activity {
    private RecyclerView recyclerview_choice;
    ArrayList<CityChoiceModel> CityChoiceModelData = new ArrayList<>();
    private String url1 = "https://devapi.qweather.com/v7/weather/now?location=";
    private String url2 = "&key=553c7d8605c544e6ba68404ace22873d";
    int id = 1;
    DBhelper dBhelper = new DBhelper(this);

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                parseWeatherData(weatherJson);
            }
        }
    };

    private void parseWeatherData(String weatherJson) {
        WeatherBeanNow weatherBeanNow = new Gson().fromJson(weatherJson, WeatherBeanNow.class);
        // 得到城市数据
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Map<String, String> rowData = getRowById(db, id);
        String city = rowData.get("city");
        String Temp = weatherBeanNow.getNow().getTemp();
        String feelTemp = weatherBeanNow.getNow().getFeelsLike();
        String weather = weatherBeanNow.getNow().getText();

        int cityId = -1; // 默认值设为 -1，表示无效的 id
        String idStr = rowData.get("id");
        if (idStr != null) {
            try {
                cityId = Integer.parseInt(idStr);//将字符串转换为整数
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // 可以在这里添加更多的错误处理逻辑，比如显示提示信息
            }
        }

        int cityIdFromData = -1; // 从数据库中获取的 cityId
        String cityIdStr = rowData.get("cityId");
        if (cityIdStr != null) {
            try {
                cityIdFromData = Integer.parseInt(cityIdStr);//将字符串转换为整数
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // 可以在这里添加更多的错误处理逻辑，比如显示提示信息
            }
        }

        if (cityId != -1 && cityIdFromData != -1) {
            CityChoiceModelData.add(new CityChoiceModel(cityId, cityIdFromData, city, weather + " 体感" + feelTemp + "℃", Temp + "℃"));
        }

        id++;
        // 通知适配器数据已更改
        if (recyclerview_choice.getAdapter() != null) {
            recyclerview_choice.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choice); // 使用正确的布局文件
        initView();
        initEvent();
    }

    private void initView() {
        recyclerview_choice = findViewById(R.id.recyclerview_choice);
    }

    private void initEvent() {
        // 初始化城市数据，从数据库和API获取城市信息并添加到 CityChoiceModelData 列表中
        cityadd();

        // 创建 CityChoiceAdapter 实例，并传入当前 Activity 和城市数据列表
        CityChoiceAdapter adapter = new CityChoiceAdapter(activity_CityChoice.this, CityChoiceModelData);

        // 设置 RecyclerView 的适配器为刚刚创建的 CityChoiceAdapter
        recyclerview_choice.setAdapter(adapter);

        // 创建 ItemTouchHelper.Callback 实例，用于处理 RecyclerView 项的拖拽和滑动删除等交互
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);

        // 创建 ItemTouchHelper 实例，并将其附加到 RecyclerView 上，以启用拖拽和滑动删除功能
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerview_choice);

        // 设置点击事件监听器，当用户点击某个城市时触发
        adapter.setOnItemClickListener(position -> {
            // 获取选中的城市信息
            CityChoiceModel selectedCity = CityChoiceModelData.get(position);

            // 创建 Intent，跳转到 MainActivity，并传递选中的城市 ID 和城市名称
            Intent intent = new Intent(activity_CityChoice.this, MainActivity.class);
            intent.putExtra("cityId", selectedCity.getCityId()); // 传递 cityId
            intent.putExtra("city", selectedCity.getCity());     // 传递城市名称

            // 打印日志，记录选中的城市 ID
            Log.d("cityId", "WeatherBeanNow" + selectedCity.getCityId());

            // 启动 MainActivity
            startActivity(intent);
        });

        // 设置 RecyclerView 的布局管理器为垂直线性布局
        recyclerview_choice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    private void cityadd() {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        List<Integer> cityId = getAllCityId(db);
        // 遍历 cityId 并调用 API
        for (int cityIds : cityId) {
            fetchWeatherData(url1 + cityIds + url2);
        }
    }


    private List<Integer> getAllCityId(SQLiteDatabase db) {
        List<Integer> cityIds = new ArrayList<>();
        String[] projection = {"cityId"};
        Cursor cursor = db.query("mytable", projection, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int cityId = cursor.getInt(cursor.getColumnIndexOrThrow("cityId"));
                cityIds.add(cityId);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return cityIds;
    }

    public void openSearchActivity(View view) {
        Intent intent = new Intent(activity_CityChoice.this, SearchActivity.class);
        startActivity(intent);
    }

    private void fetchWeatherData(String url) {
        new Thread(() -> {
            try {
                String weatherJson = NetUtil.doGet(url);
                Log.d("MainActivity", "------------Weather JSON--------------: " + weatherJson); // 输出获取到的 JSON 数据
                Message msg = mHandler.obtainMessage(0, weatherJson);
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                // 可以在这里添加异常处理逻辑，例如显示错误提示信息
            }
        }).start();
    }

    public Map<String, String> getRowById(SQLiteDatabase db, int id) {
        Map<String, String> row = new HashMap<>();
        String[] projection = {"id", "city", "cityId"}; // 定义要查询的列
        String selection = "id = ?"; // 查询条件
        String[] selectionArgs = {String.valueOf(id)}; // 条件参数
        Cursor cursor = db.query("mytable", projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // 获取各列的索引
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int nameIndex = cursor.getColumnIndexOrThrow("city");
            int cityIdIndex = cursor.getColumnIndexOrThrow("cityId");

            // 获取该行的数据
            row.put("id", String.valueOf(cursor.getInt(idIndex)));
            row.put("city", cursor.getString(nameIndex));
            row.put("cityId", String.valueOf(cursor.getInt(cityIdIndex)));
        }
        if (cursor != null) {
            cursor.close(); // 关闭 Cursor 资源
        }
        return row;
    }
}
