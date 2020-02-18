package com.WayFinder.Server.Main.RouteWeightCalculation.Bus;


public class BusCal {

    public long CO2CalBus(long distance)
    {
        long emmissions_per_BusMile = distance*0.17 //77 grams per passsanger per km --> 0.17lbs

        return emmissions_per_BusMile;

    }

}

