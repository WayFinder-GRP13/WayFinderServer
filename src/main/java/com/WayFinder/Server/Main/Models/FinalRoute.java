package com.WayFinder.Server.Main.Models;

import com.WayFinder.Server.Main.NodeCreation.Node;

public class FinalRoute{
    private Node Origin;
    private Node Destination;
    private String OverviewPolyline;
    private int RouteType;

    public FinalRoute(Node origin, Node destination, String overviewPolyline,int RouteType) {
        Origin = origin;
        Destination = destination;
        OverviewPolyline = overviewPolyline;
        this.RouteType=RouteType;
    }

    public String getOverviewPolyline() {
        return OverviewPolyline;
    }

    public void setOverviewPolyline(String overviewPolyline) {
        OverviewPolyline = overviewPolyline;
    }

    public Node getOrigin() {
        return Origin;
    }

    public void setOrigin(Node origin) {
        Origin = origin;
    }

    public Node getDestination() {
        return Destination;
    }

    public void setDestination(Node destination) {
        Destination = destination;
    }

    public int getRouteType() {
        return RouteType;
    }

    public void setRouteType(int routeType) {
        RouteType = routeType;
    }
}