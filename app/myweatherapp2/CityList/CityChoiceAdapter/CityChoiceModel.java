package com.example.myweatherapp2.CityList.CityChoiceAdapter;

public class CityChoiceModel {
    private int id;
    private int cityId; // 添加 cityId 字段
    private String city;
    private String feelTemp_weather;
    private String temp;

    public CityChoiceModel(int id, int cityId, String city, String feelTemp_weather, String temp) {
        this.id = id;
        this.cityId = cityId;
        this.city = city;
        this.feelTemp_weather = feelTemp_weather;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public int getCityId() { // 添加 getter 方法
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getFeelTemp_weather() {
        return feelTemp_weather;
    }

    public String getTemp() {
        return temp;
    }
}
