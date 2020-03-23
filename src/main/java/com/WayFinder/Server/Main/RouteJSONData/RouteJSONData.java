package com.WayFinder.Server.Main.RouteJSONData;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class RouteJSONData {

    private String directionApiUrl = "https://maps.googleapis.com/maps/api/directions/json?";
    private String apiKey = "AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE";
    HashMap<Integer,String> routeTypes = new HashMap<>();

    public ArrayList<String> getJSONpath(LinkedList<Node> pathList, ArrayList<Edge> edgeList) {
        ArrayList<String> googleRequests = new ArrayList<>();
        routeTypes.put(0,"walking");
        routeTypes.put(1,"transit");//bus
        routeTypes.put(2,"transit");//train
        routeTypes.put(3,"bicycling");
        routeTypes.put(4,"driving");//driving

        for (int i = 0; i < pathList.size() - 1; i++) {
            StringBuilder apiRequest = new StringBuilder();
            apiRequest.append(directionApiUrl);
            Node origin = pathList.get(i);
            Node destination = pathList.get(i + 1);

            //if first node
            if(i==0){
                apiRequest.append("origin="+origin.getLatitude()+","+origin.getLongitudue()+"&");
                apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
                apiRequest.append("mode=walking");
                apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");
                googleRequests.add(apiRequest.toString());
                continue;
            }
            // if last node
            if(i==pathList.size()-2){
                apiRequest.append("origin="+origin.getLatitude()+","+origin.getLongitudue()+"&");
                apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
                apiRequest.append("mode=walking");
                apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");
                googleRequests.add(apiRequest.toString());
                continue;
            }

            apiRequest.append("origin="+origin.getLatitude()+","+origin.getLongitudue()+"&");
            apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
            apiRequest.append("mode="+routeTypes.get(getTransitType(edgeList,origin,destination)));
            apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");

            googleRequests.add(apiRequest.toString());
        }

        return googleRequests;
    }

    public String getJSONpath(Node origin, Node destination, String mode) {

        StringBuilder apiRequest = new StringBuilder();
        apiRequest.append(directionApiUrl);
        apiRequest.append("origin=" + origin.getLatitude() + "," + origin.getLongitudue() + "&");
        apiRequest.append("destination=" + destination.getLatitude() + "," + destination.getLongitudue() + "&");
        apiRequest.append("mode=" + mode);
        apiRequest.append("&key=" + apiKey);
        String googleRequests = apiRequest.toString();

        return googleRequests;
    }

    private int getTransitType(ArrayList<Edge> edgeList,Node origin,Node destination){
        int transportType=0;
        for (Edge edge:edgeList){
            if(edge.getSource()==origin&&edge.getDestination()==destination){
                transportType=edge.getTransportType();
            }
        }
        return transportType;
    }

    // private int getTransitType(ArrayList<Edge> edgeList,Node origin,Node
    // destination){
    // int transportType=0;
    // for (Edge edge:edgeList){
    // if(edge.getSource()==origin&&edge.getDestination()==destination){
    // transportType=edge.getTransportType();
    // }
    // }
    // return transportType;
    // }
}
