package com.example.harjoitustyo;

public class Lake {
    private String name;
    private String id;
    private String town;
    private String drainageBasin;

    private double cordLong;
    private double cordLat;
    private int averageDepth;
    private long areaOfLake;

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public void setDrainageBasin(String drainageBasin) {
        this.drainageBasin = drainageBasin;
    }
    public void setCordinates(double cordLong,double cordLat) {
        this.cordLong = cordLong;
        this.cordLat = cordLat;
    }
    public void setAverageDepth(int averageDepth) {
        this.averageDepth = averageDepth;
    }
    public void setAreaOfLake(long areaOfLake) {
        this.areaOfLake = areaOfLake;
    }

    // get-methods:

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getTown() {
        return town;
    }
    public String getDrainageBasin() {
        return drainageBasin;
    }
    /*TODO missä muodossa halutaan koordinaatit ulos? */

    public int getAverageDepth() {
        return averageDepth;
    }
    public long getAreaOfLake() {
        return areaOfLake;
    }
}
