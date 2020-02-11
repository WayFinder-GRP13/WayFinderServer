package com.WayFinder.Server.Main.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class BusStop {
    private int busStopID;
    private double Latitude;
    private double Longitude;
    ArrayList<String> RouteList=new ArrayList<>();

    public ArrayList<String> getBusRouteList(){
        return RouteList;
    }

    public void addToBusRouteList(String route){
        RouteList.add(route);
    }

    public int getBusStopID() {
        return busStopID;
    }

    public void setBusStopID(int busStopID) {
        this.busStopID = busStopID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
