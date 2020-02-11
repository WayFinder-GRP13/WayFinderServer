package com.WayFinder.Server.Main.Models;

import java.util.ArrayList;

public class BusRoute {
    private String RouteName;
    private ArrayList<BusStop> busStopList = new ArrayList<>();

    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String routeName) {
        RouteName = routeName;
    }

    public ArrayList<BusStop> getBusStopList() {
        return busStopList;
    }

    public void addBusStop(BusStop busStop) {
        busStopList.add(busStop);
    }
}
