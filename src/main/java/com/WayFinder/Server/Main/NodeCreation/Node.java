package com.WayFinder.Server.Main.NodeCreation;

public class Node {

    private String Name;
    private int StopId;
    private int TransportType;
    private double latitude;
    private double longitudue;
    private double score;

    public Node(String Name,int StopId,int TransportType,double latitude, double longitudue, double score) {
        this.Name=Name;
        this.StopId=StopId;
        this.latitude = latitude;
        this.longitudue = longitudue;
        this.score = score;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitudue() {
        return longitudue;
    }

    public void setLongitudue(double longitudue) {
        this.longitudue = longitudue;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public int getTransportType() {
        return TransportType;
    }

    public void setTransportType(int transportType) {
        TransportType = transportType;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStopId() {
        return StopId;
    }

    public void setStopId(int stopId) {
        StopId = stopId;
    }

    public void printNode(){
        System.out.println("Node is: "+TransportType+" stop name is: "+Name+"stop ID is: "+StopId+" Lat: "+latitude+" Lng: "+longitudue);
    }
}



