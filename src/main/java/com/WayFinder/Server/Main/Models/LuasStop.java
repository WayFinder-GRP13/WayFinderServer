package com.WayFinder.Server.Main.Models;

public class LuasStop{
    public String Abbreviation;
    public double Latitude;
    public double Longitude;
    public String Pronunciation;

    public LuasStop(String abv,double lat, double lng,String pron) {
        Abbreviation=abv;
        Latitude=lat;
        Longitude=lng;
        Pronunciation=pron;
    }

    public LuasStop() {

    }
}