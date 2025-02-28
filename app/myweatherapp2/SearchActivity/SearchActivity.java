package com.example.myweatherapp2.SearchActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.CityList.activity_CityChoice;
import com.example.myweatherapp2.Internet.NetUtil;
import com.example.myweatherapp2.R;
import com.example.myweatherapp2.SearchActivity.SearchCityAdapter.SearchCityAdapter;
import com.example.myweatherapp2.SearchActivity.SearchCityAdapter.SearchCityData;
import com.example.myweatherapp2.db.DBhelper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    // RecyclerView 用于显示搜索结果
    private RecyclerView recyclerViewSearch;
    // EditText 用于用户输入搜索内容
    private EditText editTextSearch;
    // Button 用于触发搜索操作
    private Button buttonSearch;
    // 存储所有城市数据的列表
    private ArrayList<SearchCityData> allCityList;
    // 存储过滤后城市数据的列表
    private ArrayList<SearchCityData> filteredCityList;
    // RecyclerView 的适配器
    private SearchCityAdapter searchCityAdapter;
    private String city;
    private int cityId;
    private DBhelper dBhelper;

    // Handler 用于在主线程处理网络请求返回的数据
    private final Handler cityHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                parseWeatherData(weatherJson);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        // 初始化视图
        initViews();
        // 初始化事件
        initEvents();

        // 初始化城市列表
        allCityList = new ArrayList<>();
        filteredCityList = new ArrayList<>();

        // 初始化适配器并设置到 RecyclerView
        searchCityAdapter = new SearchCityAdapter(this, filteredCityList);
        recyclerViewSearch.setAdapter(searchCityAdapter);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 初始化 DBhelper 实例
        dBhelper = new DBhelper(this);

        // 设置 RecyclerView 项的点击事件监听器
        setRecyclerViewItemClickListener();
    }

    private void initViews() {
        recyclerViewSearch = findViewById(R.id.recyclerview_search);
        editTextSearch = findViewById(R.id.search_edit_text3);
        buttonSearch = findViewById(R.id.search_button4);
    }

    private void initEvents() {
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = editTextSearch.getText().toString().trim();
                if (searchTerm.isEmpty()) {
                    Toast.makeText(SearchActivity.this, "请输入城市名称", Toast.LENGTH_SHORT).show();
                } else {
                    searchCitiesOnInternet(searchTerm);
                }
            }
        });
    }

    private void setRecyclerViewItemClickListener() {
        searchCityAdapter.setOnItemClickListener(new SearchCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SearchCityData city = filteredCityList.get(position);
                Toast.makeText(SearchActivity.this, "点击了: " + city.getLocation(), Toast.LENGTH_SHORT).show();
                // 将城市名称和 ID 插入到数据库中
                String[] split = city.getLocation().split("-");
                String cityname = split[0];
                long result = dBhelper.insertCity(cityname, city.getCityId());
                if (result == -1) {
                    Toast.makeText(SearchActivity.this, "城市已存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchActivity.this, "城市已添加", Toast.LENGTH_SHORT).show();
                    // 跳转
                    Intent intent = new Intent(SearchActivity.this, activity_CityChoice.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void searchCitiesOnInternet(final String cityName) {
        new Thread(() -> {
            try {
                String weatherJson = NetUtil.doGet("https://geoapi.qweather.com/v2/city/lookup?location=" + cityName + "&key=553c7d8605c544e6ba68404ace22873d");
                Message msg = cityHandler.obtainMessage(0, weatherJson);
                cityHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(SearchActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void parseWeatherData(String weatherJson) {
        try {
            CityBean cityBean = new Gson().fromJson(weatherJson, CityBean.class);
            if (cityBean != null && cityBean.getLocation() != null) {
                allCityList.clear();
                for (CityBean.LocationBean locationBean : cityBean.getLocation()) {
                    allCityList.add(new SearchCityData(locationBean.getName() + "-" + locationBean.getAdm1() + "-" + locationBean.getAdm2() + "-" + locationBean.getCountry(), Integer.parseInt(locationBean.getId())));
                }
                filterCities("");
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Toast.makeText(this, "数据解析失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void filterCities(String searchTerm) {
        filteredCityList.clear();
        for (SearchCityData city : allCityList) {
            if (city.getLocation().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredCityList.add(city);
            }
        }
        searchCityAdapter.notifyDataSetChanged();
    }
}