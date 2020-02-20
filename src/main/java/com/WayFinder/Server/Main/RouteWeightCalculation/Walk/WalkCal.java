package com.WayFinder.Server.Main.RouteWeightCalculation.Walk;

public class WalkCal {

    public double WalkWeight(double distance) {

        double WalkW = TimeTakenWalking(distance) ;
        return WalkW;
    }



    public double TimeTakenWalking(double distance) {
        double WalkTime = distance / 4.9;
        return (WalkTime);

    }


    //walk speed average --> 4.9km/hr average
    // elderly and children  --> 3.5km
    // beginner walker --> 5km/hr
    // interemeddiate walker --> 6km/hr


}