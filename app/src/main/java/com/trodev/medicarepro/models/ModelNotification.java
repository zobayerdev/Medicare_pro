package com.trodev.medicarepro.models;

public class ModelNotification {

    public String titles, desc, date, key;

    public ModelNotification() {
    }

    public ModelNotification(String titles, String desc, String date, String key) {
        this.titles = titles;
        this.desc = desc;
        this.date = date;
        this.key = key;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
