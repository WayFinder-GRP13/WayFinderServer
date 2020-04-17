package com.WayFinder.Server.Main.RouteWeightCalculation.Bus;


public class BusCal {

    public double BusWeight(double distance) {
        double BusW = CO2CalBus(distance) + SpeedBus(distance) + costBus(); //7.00 is the cost
        return BusW;
    }


    public double CO2CalBus(double distance) {
        double emmissions_per_BusMile = distance * 0.17; //77 grams per passsanger per km --> 0.17lbs
        return emmissions_per_BusMile;
    }

    public double SpeedBus(double distance) {
        double BusTime = distance / 60.00;
        return (BusTime);
    }
    public double costBus()
    {
        return 7.00;
    }

}
//speed --> distance based on average speed of transport --> 65km/h
// cost --> solid --> 7 euro a day

