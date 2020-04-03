package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.NodeCreation.Node;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NodeMinimisationManager {
    private NodeMinimisation nodeMinimisation;

    public NodeMinimisationManager(){
        nodeMinimisation = new NodeMinimisation();
    }

    public ArrayList<Node> getBusNodes(ArrayList<Node> initialList, LatLng startLocation, LatLng EndLocation){
        // finds the first route match between the nodes at start and end points
        ArrayList<String> matchingBusRoutes = nodeMinimisation.findMatchingBusRoutes(initialList);
        for(String route:matchingBusRoutes){
            System.out.println("Route is: "+route);
        }
        // get further bus routes if needed
        //nodeMinimisation.furtherBusRouteMatches(initialList);
        // gets stop list from route
        ArrayList<Node> busStopList = nodeMinimisation.getStopList(initialList,startLocation,EndLocation);

        //ArrayList<Node> reducedStopList = nodeMinimisation.ReduceNodes(busStopList,startLocation, EndLocation);

        // returns the minimised list

        return busStopList;
    }

    public ArrayList<Node> getLuasNodes(ArrayList<Node> initialList, LatLng startLocation, LatLng EndLocation){
        // finds the first route match between the nodes at start and end points
        ArrayList<Node> luasStopList = nodeMinimisation.findMatchingLuasStops(initialList,startLocation,EndLocation);
        for(Node bus : luasStopList){
            System.out.println("stop is: "+bus.getStopId());
        }
        return luasStopList;
    }

    public MinimizedPaths minimiseToFinalNodes(ArrayList<Node> BusStopsNodes, ArrayList<Node> LuasStopsNodes){
        int numOfIntermediateNodes=4;
        ArrayList<ShorestNodePair> LuasBusDistances = new ArrayList<>();


        for (Node busStop : BusStopsNodes){

            double smallestDistance = 9999999.0;
            Node closestLuasStop = null;

            for(Node LuasStop : LuasStopsNodes){
                double distance = nodeMinimisation.distanceTo(LuasStop.getLatitude(),LuasStop.getLongitudue(),busStop.getLatitude(),busStop.getLongitudue(),"K");
                if (distance < smallestDistance){
                    closestLuasStop = LuasStop;
                    smallestDistance = distance;
                }
            }
            LuasBusDistances.add(new ShorestNodePair(busStop,closestLuasStop,smallestDistance));
        }
        for(ShorestNodePair pair :LuasBusDistances){
            System.out.println("pair: "+pair.getBusStop().getStopId()+" + "+pair.getLuasStop().getStopId()+" Distance: "+pair.getDistance());
        }
        LuasBusDistances.sort((o1, o2) -> Double.compare( o1.getDistance(),o2.getDistance()));

        for(ShorestNodePair pair :LuasBusDistances){
            System.out.println("Sorted pair: "+pair.getBusStop().getStopId()+" + "+pair.getLuasStop().getStopId()+" Distance: "+pair.getDistance());
        }

        // add final bus and luas path to this object
        ArrayList<Node> LuasStopList = new ArrayList<>();
        ArrayList<Node> BusStopList = new ArrayList<>();
        MinimizedPaths minimizedPaths = new MinimizedPaths(BusStopList,LuasStopList);


        // add start and end node
        BusStopList.add(BusStopsNodes.get(0));
        Collections.sort(LuasStopsNodes, Comparator.comparingDouble(Node::getDistanceToStartLocation));
        LuasStopList.add(LuasStopsNodes.get(0));


            for(ShorestNodePair pair : LuasBusDistances){
                if(BusStopList.size()<numOfIntermediateNodes&&!BusStopList.contains(pair.getBusStop())){
                    BusStopList.add(pair.getBusStop());
                }
                if(LuasStopList.size()<numOfIntermediateNodes&&!LuasStopList.contains(pair.getLuasStop())){
                    LuasStopList.add(pair.getLuasStop());
                }
            }

        BusStopList.add(BusStopsNodes.get(BusStopsNodes.size()-1));
        Collections.sort(LuasStopsNodes, Comparator.comparingDouble(Node::getDistanceToEndLocation));
        LuasStopList.add(LuasStopsNodes.get(0));
        return minimizedPaths;
    }

    public ArrayList<Node> getMinimizedBusList(ArrayList<Node> busStopList, LatLng startLocation, LatLng EndLocation){
        return nodeMinimisation.ReduceBusNodes(busStopList,startLocation, EndLocation);
    }


    public ArrayList<Node> getMinimizedLuasList(ArrayList<Node> luasStopList) {
        return nodeMinimisation.ReduceLuasNodes(luasStopList);
    }
}
