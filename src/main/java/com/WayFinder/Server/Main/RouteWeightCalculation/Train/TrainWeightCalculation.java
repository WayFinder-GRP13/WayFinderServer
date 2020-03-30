package com.WayFinder.Server.Main.RouteWeightCalculation.Train;

import com.WayFinder.Server.Main.NodeCreation.Node;

public class TrainWeightCalculation {

//    public double TrainWeight(double distance) {
//        double TrainW = CO2CalTrain(distance) + SpeedOfTrain(distance) + (10.00);
//        return (TrainW);
//    }
//
//    public double CO2CalTrain(double distance) {
//
//        double emmissions_per_TrainMile = distance * 0.41; // 0.41 --> dart an luas are different
//
//        return emmissions_per_TrainMile;
//
//    }
//
//    public double SpeedOfTrain(double distance) {
//        double TrainTime = distance / 30.00;
//        return (TrainTime);
//    }


    public int getNodeWeight(Node firstNode, Node secondNode) {
        double distance = Distance(firstNode.getLatitude(),firstNode.getLongitudue(),secondNode.getLatitude(),secondNode.getLongitudue(),"K");
        double timeTaken = distance * 30;
        return (int)Math. ceil(timeTaken);
    }

    public double Distance(double lat1,double lon1,double lat2,double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        }
        return dist;
    }
}

// Luas --> 30km/h
// dart --> 30 km/h



