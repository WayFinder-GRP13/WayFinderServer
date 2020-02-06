package com.WayFinder.Server.Main.NodeCreation;

public class Node {
    
    int TransportType;
    double latitude;
    double longitudue;
    double score;

    public Node(int TransportType,double latitude, double longitudue, double score) {
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


}



