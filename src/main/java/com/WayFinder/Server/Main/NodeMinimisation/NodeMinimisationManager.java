package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.NodeCreation.Node;
import com.google.maps.model.LatLng;

import java.util.ArrayList;

public class NodeMinimisationManager {
    private NodeMinimisation nodeMinimisation;

    public NodeMinimisationManager(){
        nodeMinimisation = new NodeMinimisation();
    }

    public ArrayList<Node> minimiseNodes(ArrayList<Node> initialList, LatLng startLocation, LatLng EndLocation){
        // finds the first route match between the nodes at start and end points
        ArrayList<String> matchingRoutes = nodeMinimisation.findMatchingRoutes(initialList);
        for(String route:matchingRoutes){
            System.out.println("Route is: "+route);
        }
        // from the matched index check the next x stops for further matches in routes
        nodeMinimisation.furtherBusRouteMatches(initialList);
        // returns the minimised list
        return nodeMinimisation.minimiseNodeList(initialList,startLocation, EndLocation);
    }

}
