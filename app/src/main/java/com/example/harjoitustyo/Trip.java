package com.example.harjoitustyo;

import java.sql.Time;

public class Trip {
    private Time time;
    private Lake lake;
    private String description = "";

    public Trip(Time time, Lake lake) {
        this.time = time;
        this.lake = lake;
    }

    public Trip(Time time, Lake lake, String description) {
        this.time = time;
        this.lake = lake;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Lake getLake() {
        return lake;
    }
    public void setLake(Lake lake) {
        this.lake = lake;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }
}
