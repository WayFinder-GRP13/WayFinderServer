package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;

public class MinimizedPaths {
    private ArrayList<Node> busStopList;
    private ArrayList<Node> LuasStopList;

    public MinimizedPaths(ArrayList<Node> busStopList, ArrayList<Node> luasStopList) {
        this.busStopList=busStopList;
        this.LuasStopList=luasStopList;
    }

    public ArrayList<Node> getBusStopList() {
        return busStopList;
    }

    public void setBusStopList(ArrayList<Node> busStopList) {
        this.busStopList = busStopList;
    }

    public ArrayList<Node> getLuasStopList() {
        return LuasStopList;
    }

    public void setLuasStopList(ArrayList<Node> luasStopList) {
        LuasStopList = luasStopList;
    }
}
