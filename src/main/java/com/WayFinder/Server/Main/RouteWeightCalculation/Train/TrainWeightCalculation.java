package com.WayFinder.Server.Main.RouteWeightCalculation.Train;

import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.PerferenceCalculation.PreferenceCalculationManager;

public class TrainWeightCalculation {


    public double getNodeWeight(Node firstNode, Node secondNode) {
        final PreferenceCalculationManager prefer = new PreferenceCalculationManager();
        double distance = Distance(firstNode.getLatitude(),firstNode.getLongitudue(),secondNode.getLatitude(),secondNode.getLongitudue(),"K");
        double Trainweight = prefer.getTrain(distance);
        //double timeTaken = distance * 30;
        //return (int)Math. ceil(timeTaken);
        return(Trainweight);

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



