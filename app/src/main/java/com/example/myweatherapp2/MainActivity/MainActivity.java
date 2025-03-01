package com.example.myweatherapp2.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp2.CityList.activity_CityChoice;
import com.example.myweatherapp2.Internet.NetUtil;
import com.example.myweatherapp2.MainAdpter.SevenDayAdpter;
import com.example.myweatherapp2.MainAdpter.WeatherData;
import com.example.myweatherapp2.R;
import com.example.myweatherapp2.db.dbManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView textview_weather_city, textview_date_time, textview_temperature,
            textview_temperature_range_weather, textview_humidity_value, textview_wind_speed_value,
            textview_rain_probability_value;
    private ImageView imageview_weather_icon;
    private RecyclerView recyclerview_forecast;
    private String url1="https://devapi.qweather.com/v7/weather/7d?location=";
    private String url1_2="https://devapi.qweather.com/v7/weather/now?location=";
    private String url_3="http://restapi.amap.com/v3/ip?key=a6c1cb2db33531f3b58a4e29c1aa02de";
    private String url2="&key=553c7d8605c544e6ba68404ace22873d";
    private int cityID;
    private String city;
    private String url,url_2;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weatherJson = (String) msg.obj;
                parseWeatherData(weatherJson);
            }else if (msg.what == 1){
                String weatherJson = (String) msg.obj;
                parseWeatherData2(weatherJson);
            }
        }
    };
    ArrayList<WeatherData>weatherData=new ArrayList<>();
//利用gson解析数据
private void parseWeatherData(String weatherJson) {
    WeatherBean weatherBean = new Gson().fromJson(weatherJson, WeatherBean.class);
    if (weatherBean != null && weatherBean.getDaily() != null && weatherBean.getDaily().size() >= 7) {
        for (int i = 0; i < 7; i++) {
            WeatherBean.DailyBean SevendailyBean = weatherBean.getDaily().get(i);
            String icon = SevendailyBean.getIconDay();
            int iconResId = getIconResId(icon);
            weatherData.add(new WeatherData(SevendailyBean.getFxDate(), iconResId, SevendailyBean.getTempMin() + "-" + SevendailyBean.getTempMax() + "℃"));
        }
        SevenDayAdpter sevenDayAdpter = new SevenDayAdpter(MainActivity.this, weatherData);
        recyclerview_forecast.setAdapter(sevenDayAdpter);
        recyclerview_forecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        WeatherBean.DailyBean dailyBean = weatherBean.getDaily().get(0);
        String NowIcon = dailyBean.getIconDay();
        textview_temperature_range_weather.setText(dailyBean.getTextDay() + "  " + dailyBean.getTempMin() + "  ⁓ " + dailyBean.getTempMax() + "℃");
        textview_humidity_value.setText(dailyBean.getHumidity() + "%");
        textview_wind_speed_value.setText(dailyBean.getWindSpeedDay() + "KM/H");
        textview_rain_probability_value.setText(dailyBean.getWindDirDay());
        imageview_weather_icon.setImageResource(getIconResId(NowIcon));
    } else {
        // 处理数据为空的情况，例如显示默认文本
        textview_temperature_range_weather.setText("暂无数据");
        textview_humidity_value.setText("暂无数据");
        textview_wind_speed_value.setText("暂无数据");
        textview_rain_probability_value.setText("暂无数据");
        imageview_weather_icon.setImageResource(R.drawable.fog); // 替换为默认图标资源 ID
        Log.e("MainActivity", "WeatherBean or daily list is null or size is less than 7. JSON: " + weatherJson);
    }
}

    private int getIconResId(String icon) {
        // 根据 iconCode 返回对应的资源 ID
        switch (icon) {
            case "100": return R.drawable.sunny;
            case "101": return R.drawable.cloudy;
            case "102": return R.drawable.cloudy;
            case "103": return R.drawable.cloudy;
            case "305": return R.drawable.showers;
            case "306": return R.drawable.showers;
            case "307": return R.drawable.showers;
            case "308": return R.drawable.showers;
            case "309": return R.drawable.showers;
            case "310": return R.drawable.showers;
            // 添加其他图标映射
            default: return R.drawable.sunny;
        }
    }

    private void parseWeatherData2(String weatherJson) {
        WeatherBeanNow weatherBeanNow = new Gson().fromJson(weatherJson, WeatherBeanNow.class);
        if (weatherBeanNow != null && weatherBeanNow.getNow() != null) {
            textview_temperature.setText(weatherBeanNow.getNow().getTemp() + "℃");
        } else {
            // 处理数据为空的情况，例如显示默认文本
            textview_temperature.setText("暂无数据");
            Log.e("MainActivity", "WeatherBeanNow or NowBean is null. JSON: " + weatherJson);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent(); // 先初始化事件，确保 url 被正确赋值
        fetchWeatherData();

    }


//绑定视图
    private void initView() {
        textview_weather_city = findViewById(R.id.textview_weather_city);
        imageview_weather_icon = findViewById(R.id.imageview_weather_icon);
        textview_date_time = findViewById(R.id.textview_date_time);
        textview_temperature = findViewById(R.id.textview_temperature);
        textview_temperature_range_weather = findViewById(R.id.textview_temperature_range_weather);
        textview_humidity_value = findViewById(R.id.textview_humidity_value);
        textview_wind_speed_value = findViewById(R.id.textview_wind_speed_value);
        textview_rain_probability_value = findViewById(R.id.textview_rain_probability_value);
        recyclerview_forecast = findViewById(R.id.recyclerview_forecast);
        dbManager.initDB(this);
        setDate();
    }
//获取本地时间信息
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String dayInChinese = convertDayToChinese(calendar.get(Calendar.DAY_OF_WEEK));

        String timeText = String.format("星期%s  %d月%d日| %02d:%02d", dayInChinese, month, day, hour, minute);
        textview_date_time.setText(timeText);
    }
    private String convertDayToChinese(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:    return "日";
            case Calendar.MONDAY:    return "一";
            case Calendar.TUESDAY:   return "二";
            case Calendar.WEDNESDAY: return "三";
            case Calendar.THURSDAY:  return "四";
            case Calendar.FRIDAY:    return "五";
            case Calendar.SATURDAY:  return "六";
            default:                 return "";
        }
    }
//获取其他页面传输过来的城市信息
    private void initEvent() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            cityID=bundle.getInt("cityId");
            city=bundle.getString("city");
        }else {
            cityID=101250101;
            city="长沙";
        }
        url=url1+cityID+url2;
        url_2=url1_2+cityID+url2;
        textview_weather_city.setText(city);
    }
    //解析数据
    private void fetchWeatherData() {
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
        new Thread(() -> {
            try {
                String weatherJson = NetUtil.doGet(url_2);
                Log.d("MainActivity", "------------Weather JSON--------------: " + weatherJson); // 输出获取到的 JSON 数据
                Message msg = mHandler.obtainMessage(1, weatherJson);
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                // 可以在这里添加异常处理逻辑，例如显示错误提示信息
            }
        }).start();
        new Thread(() -> {
            try {
                String weatherJson = NetUtil.doGet(url_3);
                Log.d("MainActivity", "------------Weather JSON--------------: " + weatherJson); // 输出获取到的 JSON 数据
                Message msg = mHandler.obtainMessage(2, weatherJson);
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                // 可以在这里添加异常处理逻辑，例如显示错误提示信息
            }
        }).start();
    }


    public void openNewActivity(View view) {
        Intent intent=new Intent(MainActivity.this,activity_CityChoice.class);
        startActivity(intent);
    }
}