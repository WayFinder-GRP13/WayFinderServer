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
        int skipCounter = 0;
        Node skipOrigin = null;

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
            if(i==pathList.size()-2&&skipCounter==0){
                apiRequest.append("origin="+origin.getLatitude()+","+origin.getLongitudue()+"&");
                apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
                apiRequest.append("mode=walking");
                apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");
                googleRequests.add(apiRequest.toString());
                continue;
            }
            //if transport route is bus or train stop and next one is also that then skip this one and make
            // it one long trip
            if((origin.getTransportType()==1||origin.getTransportType()==2)&&origin.getTransportType()==destination.getTransportType()&&destination.getTransportType()==pathList.get(i + 2).getTransportType()){
                System.out.println("got inside this if loop");
                skipCounter+=1;
                if(skipOrigin==null){
                    skipOrigin=origin;
                }
                continue;
            }
            if(skipCounter!=0){

                apiRequest.append("origin="+skipOrigin.getLatitude()+","+skipOrigin.getLongitudue()+"&");
                apiRequest.append("destination="+destination.getLatitude()+","+destination.getLongitudue()+"&");
                apiRequest.append("mode="+routeTypes.get(getTransitType(edgeList,pathList.get(i-1),origin))+"&transit_mode=tram&");
                apiRequest.append("&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE");
                googleRequests.add(apiRequest.toString());
                System.out.println("got inside this api setting loop: "+apiRequest.toString());
                skipCounter=0;
                skipOrigin=null;
                continue;

            }

            // finally default behaviour
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

    public int ParseAPIRequest(String apiRequest){
        if(apiRequest.contains("walking")){
            return 0;
        }
        if(apiRequest.contains("transit")){
            return 1;
        }
        if(apiRequest.contains("cycling")){
            return 3;
        }
        return 0;
    }

    public Node getNodeFromString(String APIRequest, LinkedList<Node> finalPath, String nodeType){
        double latitude;
        double longitude;
        if(nodeType=="ogn"){
            latitude = Double.parseDouble(APIRequest.substring(APIRequest.indexOf("origin=") + 7, APIRequest.indexOf(",")));
            longitude = Double.parseDouble(APIRequest.substring(APIRequest.indexOf(",") + 1, APIRequest.indexOf("&destination")));
            System.out.println("Lati: "+latitude);
            System.out.println("longi: "+longitude);
        }else{
            //System.out.println(APIRequest.substring(APIRequest.indexOf("destination=") + 12, APIRequest.indexOf(",",APIRequest.indexOf("destination="))));
            latitude = Double.parseDouble(APIRequest.substring(APIRequest.indexOf("destination=") + 12, APIRequest.indexOf(",",APIRequest.indexOf("destination="))));
            longitude = Double.parseDouble(APIRequest.substring(APIRequest.indexOf(",",APIRequest.indexOf("destination=")) + 1, APIRequest.indexOf("&",APIRequest.indexOf("destination="))));
            System.out.println("dest Lati: "+latitude);
            System.out.println("dest longi: "+longitude);
        }
        for(Node node : finalPath){

            if(node.getLatitude()==latitude&&node.getLongitudue()==longitude){
                return node;
            }
        }
        return null;
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
