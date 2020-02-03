package com.WayFinder.Server.Main.Model;

import com.WayFinder.Server.Main.UserSettings.UserSettings;
import com.google.maps.model.LatLng;

public class RestAPIRequestInformation {
    private int id;
    private String name;
    private LatLng startLocation;
    private LatLng endLocation;
    private UserSettings settings;

    RestAPIRequestInformation(int id, String name, String startLocationLat,String startLocationLong,String endLocationLat,String endLocationLong,UserSettings settings){
        this.id = id;
        this.name = name;
        double startLocationLatD = Double.parseDouble(startLocationLat);
        double startLocationLongD = Double.parseDouble(startLocationLong);
        double endLocationLatD = Double.parseDouble(endLocationLat);
        double endLocationLongD = Double.parseDouble(endLocationLong);
        this.startLocation = new LatLng(startLocationLatD,startLocationLongD);
        this.endLocation = new LatLng(endLocationLatD,endLocationLongD);
        this.settings = settings;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }
}
