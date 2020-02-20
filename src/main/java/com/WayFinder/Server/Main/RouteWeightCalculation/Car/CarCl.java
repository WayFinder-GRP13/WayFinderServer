package com.WayFinder.Server.Main.RouteWeightCalculation.Car;

public class CarCl {

    public double CarWeight(double distance) {

        double CarW = CO2CalCar(distance) + SpeedOfCar(distance) + 10.00;
        return CarW;
    }

    public double CO2CalCar(double distance) {
        double emmissions_per_CarMile = distance * (double) 1.17; //1.17lbs

        return emmissions_per_CarMile;

    }

    public double SpeedOfCar(double distance) {
        double CarTime = distance / 72.00;
        return (CarTime);

    }

    public double CostOfCar(double distance) {
        double CarPetrol = distance * 1.60;
        return (CarPetrol);
    }


}