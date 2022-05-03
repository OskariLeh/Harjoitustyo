package com.example.harjoitustyo;

import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;

public class Trip implements Serializable {
    private String time;
    private String lakeName;
    private String duration = "";
    private String description = "";

    public Trip(){
    }

    public Trip(String time, String lakeName) {
        this.time = time;
        this.lakeName = lakeName;
    }

    public Trip(String lakeName, String description, String duration, String time) {
        this.lakeName = lakeName;
        this.description = description;
        this.duration = duration;
        this.time = time;
    }


    public String getTime() {
        return time;
    }
    public String getLake() {
        return lakeName;
    }
    public String getDuration() {return duration;}
    public String getDescription() {
        return description;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void setLake(String lakeName) {
        this.lakeName = lakeName;
    }
    public void setDuration(String duration){this.duration = duration; }
    public void setDescription(String description) {
        this.description = description;
    }


}
