package com.example.harjoitustyo;

import java.io.Serializable;

public class Lake implements Serializable {
    private String name;
    private int id;
    private String town;
    private String drainageBasin;

    private Double cordLong;
    private Double cordLat;
    private double averageDepth;
    private double areaOfLake;


    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public void setDrainageBasin(String drainageBasin) {
        this.drainageBasin = drainageBasin;
    }
    public void setCordinates(String cordLong, String cordLat) {
        this.cordLong = Double.parseDouble(cordLong);
        this.cordLat = Double.parseDouble(cordLat);
    }
    public void setAverageDepth(double averageDepth) {
        this.averageDepth = averageDepth;
    }
    public void setAreaOfLake(double areaOfLake) {
        this.areaOfLake = areaOfLake;
    }

    // get-methods:

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getTown() {
        return town;
    }
    public String getDrainageBasin() {
        return drainageBasin;
    }
    public Double getCordLong() {return cordLong;}
    public Double getCordLat() {
        return cordLat;
    }

    public double getAverageDepth() {
        return averageDepth;
    }
    public double getAreaOfLake() {
        return areaOfLake;
    }
}
