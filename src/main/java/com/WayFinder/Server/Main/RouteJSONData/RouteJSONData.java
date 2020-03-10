package com.WayFinder.Server.Main.RouteJSONData;

import com.WayFinder.Server.Main.Constants.RouteTypes;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class RouteJSONData {

    public ArrayList<String> getJSONpath(LinkedList<Node> pathList, ArrayList<Edge> edgeList){
        ArrayList<String> googleRequests = new ArrayList<>();
        
        for(int i=0;i<pathList.size()-1;i++){
            StringBuilder apiRequest = new StringBuilder();
            apiRequest.append("https://maps.googleapis.com/maps/api/directions/json?");
            Node origin = pathList.get(i);
            Node destination = pathList.get(i+1);

            apiRequest.append("origin="+origin.getLatitude()+","+origin.getLongitudue()+"&");
            apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
            apiRequest.append("mode="+RouteTypes.getRouteType(edgeList.get(i).getTransportType()));//+routeTypes.get(getTransitType(edgeList,origin,destination)));
            apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");

            googleRequests.add(apiRequest.toString());
        }
        
        return googleRequests;
    }

    // private int getTransitType(ArrayList<Edge> edgeList,Node origin,Node destination){
    //     int transportType=0;
    //     for (Edge edge:edgeList){
    //         if(edge.getSource()==origin&&edge.getDestination()==destination){
    //             transportType=edge.getTransportType();
    //         }
    //     }
    //     return transportType;
    // }
}
