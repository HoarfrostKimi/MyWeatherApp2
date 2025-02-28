package com.example.myweatherapp2.MainAdpter;

public class WeatherData {
    private String data;
    private int WeatherIcon;
    private String temp;

    public WeatherData(String data, int weatherIcon, String temp) {
        this.data = data;
        WeatherIcon = weatherIcon;
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
