package com.example.myweatherapp2.db;

public class databaseBean {
    private int id;
    private String city;
    private String context;

    public databaseBean() {
    }

    public databaseBean(int id, String city, String context) {
        this.id = id;
        this.city = city;
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
