package com.example.harjoitustyo;

import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;

public class Trip implements Serializable {
    private Time time;
    private Lake lake;
    private String duration = "";
    private String description = "";

    public Trip(){
    }

    public Trip(Time time, Lake lake) {
        this.time = time;
        this.lake = lake;
    }

    public Trip(Lake lake, String description, String duration) {
        this.lake = lake;
        this.description = description;
        this.duration = duration;
    }


    public Time getTime() {
        return time;
    }
    public Lake getLake() {
        return lake;
    }
    public String getDuration() {return duration;}
    public String getDescription() {
        return description;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    public void setLake(Lake lake) {
        this.lake = lake;
    }
    public void setDuration(String duration){this.duration = duration; }
    public void setDescription(String description) {
        this.description = description;
    }


}
