package com.WayFinder.Server.Main.Model;


public class LocationPoint {
    private double lat;
    private double lng;


    public LocationPoint(double Latitude,double Longitude) {
        lat =Latitude;
        lng =Longitude;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
