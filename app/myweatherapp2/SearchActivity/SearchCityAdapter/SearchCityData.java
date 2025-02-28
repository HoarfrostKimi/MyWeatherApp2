package com.example.myweatherapp2.SearchActivity.SearchCityAdapter;

public  class SearchCityData {
    private String location;
    private int cityId;

    public SearchCityData(String location, int cityId) {
        this.location = location;
        this.cityId = cityId;
    }

    public String getLocation() {
        return location;
    }

    public int getCityId() {
        return cityId;
    }
}

