package com.WayFinder.Server.Main;

import com.google.maps.model.LatLng;

public class RestAPIInformation {
    private long id;
    private String name;
    private LatLng startLocation;
    private LatLng endLocation;

    RestAPIInformation(long id, String name, String startLocationLat,String startLocationLong,String endLocationLat,String endLocationLong){
        this.id = id;
        this.name = name;
        double startLocationLatD = Double.parseDouble(startLocationLat);
        double startLocationLongD = Double.parseDouble(startLocationLong);
        double endLocationLatD = Double.parseDouble(endLocationLat);
        double endLocationLongD = Double.parseDouble(endLocationLong);
        this.startLocation = new LatLng(startLocationLatD,startLocationLongD);
        this.endLocation = new LatLng(endLocationLatD,endLocationLongD);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }
}
