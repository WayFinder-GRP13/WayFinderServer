package com.WayFinder.Server.Main.Models;

public class LuasInfo {
    public String LuasLine;
    public int IndexInfo;
    private double distance;

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }
}
