package com.WayFinder.Server.Main.RouteWeightCalculation.Train;

public class TrainCal {

    public long CO2CalTrain(long distance)
    {

        long emmissions_per_TrainMile =  distance*(long)0.41; // 0.41 --> dart an luas are different

        return emmissions_per_TrainMile;

    }

}

