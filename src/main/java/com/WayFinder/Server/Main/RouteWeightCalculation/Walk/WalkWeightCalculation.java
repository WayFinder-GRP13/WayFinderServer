package com.WayFinder.Server.Main.RouteWeightCalculation.Walk;

import com.WayFinder.Server.Main.NodeCreation.Node;

public class WalkWeightCalculation {

    public int getNodeWeight(Node firstNode, Node secondNode) {
        double distance = Distance(firstNode.getLatitude(),firstNode.getLongitudue(),secondNode.getLatitude(),secondNode.getLongitudue(),"K");
        double timeTaken = distance * 4.9;
        return (int)Math. ceil(timeTaken);
    }


    //walk speed average --> 4.9km/hr average
    // elderly and children  --> 3.5km
    // beginner walker --> 5km/hr
    // interemeddiate walker --> 6km/hr


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