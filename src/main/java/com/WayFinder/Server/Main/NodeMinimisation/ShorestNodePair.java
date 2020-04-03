package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public class ShorestNodePair {
    private Node BusStop;
    private Node LuasStop;
    private double distance;

    public ShorestNodePair(Node BusStop, Node LuasStop, double smallestDistance) {
        this.BusStop=BusStop;
        this.LuasStop=LuasStop;
        this.distance=smallestDistance;
    }

    public Node getBusStop() {
        return BusStop;
    }

    public void setBusStop(Node BusStop) {
        this.BusStop = BusStop;
    }

    public Node getLuasStop() {
        return LuasStop;
    }

    public void setLuasStop(Node LuasStop) {
        this.LuasStop = LuasStop;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }



}
