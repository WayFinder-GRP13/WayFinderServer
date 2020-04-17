package com.WayFinder.Server.Main.RouteWeightCalculation.Train;

public class TrainCal {

    public double TrainWeight(double distance) {
        double TrainW = CO2CalTrain(distance) + SpeedOfTrain(distance) + CostTrain();
        return (TrainW);
    }

    public double CO2CalTrain(double distance) {

        double emmissions_per_TrainMile = distance * 0.41; // 0.41 --> dart an luas are different

        return emmissions_per_TrainMile;

    }

    public double SpeedOfTrain(double distance) {
        double TrainTime = distance / 30.00;
        return (TrainTime);
    }

    public double CostTrain()
    {
        return 10.00;
    }



}

// Luas --> 30km/h
// dart --> 30 km/h



