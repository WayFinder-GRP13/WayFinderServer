package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.RestController.BusAPIController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NodeMinimisation {
    BusAPIController busAPIController = new BusAPIController();

    //this finds routes that connect nodes in our map and gets rid of none important nodes
    public ArrayList<BusStop> minimiseBusStops(ArrayList<Node> initialStopList){
        ArrayList<Node> SortedDistanceStartNodes=new ArrayList<>();
        ArrayList<Node> SortedDistanceEndNodes=new ArrayList<>();
        ArrayList<BusStop> minimisedBusStopList = new ArrayList<>();

        //sort distance into smallest to largest
        Collections.sort(initialStopList, Comparator.comparingDouble(Node::getDistanceToStartLocation));

        // does a deep copy of the sorted array of start location distances
        for(Node node:initialStopList){
            //Add the object clones
            SortedDistanceStartNodes.add(new Node(node));
        }
        for(Node node: initialStopList){
            System.out.println(node.getDistanceToStartLocation());
        }

        //sorts distance from smallest to largest for end node
        Collections.sort(initialStopList, Comparator.comparingDouble(Node::getDistanceToEndLocation));


        // does a deep copy of the sorted array of end location distances
        for(Node node:initialStopList){
            //Add the object clones
            SortedDistanceEndNodes.add(new Node(node));
        }


        //this cycles through bus stops comparing each start stop to the first and next end stop for
        // linking routes together.
        BusStop StartStopInformation=null;
        BusStop EndStopInformation=null;
        for(int i=0;i<SortedDistanceStartNodes.size();i++){
            Node startBusStop = SortedDistanceStartNodes.get(i);
            try {
                ArrayList<BusStop> busStopList = busAPIController.getBusStopInfo(startBusStop.getStopId());
                StartStopInformation=busStopList.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int j=i;j<i+1;j++){
                Node endBusStop;
                //this if statement will break the loop if we are going outside indexes
                if(j<SortedDistanceEndNodes.size()) {
                    endBusStop = SortedDistanceEndNodes.get(i);
                }else{
                    break;
                }
                // compare for equal routes
                try {
                    ArrayList<BusStop> busStopList = busAPIController.getBusStopInfo(endBusStop.getStopId());
                    EndStopInformation=busStopList.get(0);
                    StartStopInformation.getBusRouteList().retainAll(EndStopInformation.getBusRouteList());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("final route list");
            System.out.println(StartStopInformation.getBusRouteList());
        }

        try {
            //for each route that was linked get bus stops on the route and inside the bounding box
            for(String RouteNumber:StartStopInformation.getBusRouteList()) {
                BusRoute route = busAPIController.getRouteInformation(Integer.parseInt(RouteNumber));

                for (Node busStop : initialStopList) {
                    for (BusStop routeBusStop : route.getBusStopList()) {
                        if (busStop.getStopId() == routeBusStop.getBusStopID()) {
                            minimisedBusStopList.add(routeBusStop);
                        }
                    }
                }
            }

           //remove duplicates
            for(int i=minimisedBusStopList.size()-1; i>0; i--) {
                for(int j=i-1; j>=0; j--) {
                    if(minimisedBusStopList.get(i).getBusStopID()==minimisedBusStopList.get(j).getBusStopID()){
                        minimisedBusStopList.remove(i);
                        break;
                    }
                }
            }
            System.out.println("Dup removed: Final bus list");
            for(BusStop stop:minimisedBusStopList) {
                System.out.println("ID: "+stop.getBusStopID());
                for(String route:stop.getBusRouteList()) {
                    System.out.println("Route: " + route);
                }
                System.out.println("lat: "+stop.getLatitude());
                System.out.println("lng: "+stop.getLongitude());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return minimisedBusStopList;
    }
}