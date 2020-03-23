package com.WayFinder.Server.Main.Models;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;

public class BusRoute {
    private String RouteName;
    private ArrayList<Node> busStopList = new ArrayList<>();

    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String routeName) {
        RouteName = routeName;
    }

    public ArrayList<Node> getBusStopList() {
        return busStopList;
    }

    public void addBusStop(Node busStop) {
        busStopList.add(busStop);
    }

    public void setBusStopList(ArrayList<Node> filteredStopList) {
        this.busStopList=filteredStopList;
    }
}
