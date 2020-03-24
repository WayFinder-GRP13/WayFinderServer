package com.WayFinder.Server.Main.Parsers;

public class RouteResponse {
    private String overviewPolyline;
    private String route;
    private String departureTime;
    private int length;

    public RouteResponse(String overviewPolyline, String route, String departureTime, int length) {
        this.overviewPolyline = overviewPolyline;
        this.route = route;
        this.departureTime = departureTime;
        this.length = length;
    }

    public String getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(String overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
