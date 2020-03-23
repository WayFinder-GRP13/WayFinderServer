package com.WayFinder.Server.Main.NodeMinimisation;

import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusRouteList;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.RestController.BusAPIController;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class NodeMinimisation {
    BusAPIController busAPIController = new BusAPIController();
    // this values gets the closest nodes to start and end points
    private int CloseNodeCheckAmount = 1;
    private int MatchedIndex;
    ArrayList<Node> SortedDistanceStartNodes = new ArrayList<>();
    ArrayList<Node> SortedDistanceEndNodes = new ArrayList<>();
    ArrayList<String> MatchingRouteStops = new ArrayList<>();


    //     this method cycles through bus stops in order checking
//    A-Z A-Y Y-B
//     where A is closest node to start point and
//     Z is closest node to end point
//     if no match is found it will return null
    public ArrayList<String> findMatchingRoutes(ArrayList<Node> initialStopList) {


        //sort distance from shortest node to start point to furthest node to start point
        Collections.sort(initialStopList, Comparator.comparingDouble(Node::getDistanceToStartLocation));

        // does a deep copy of the sorted array of start location distances
        for (Node node : initialStopList) {
            //Add the object clones
            SortedDistanceStartNodes.add(new Node(node));
            System.out.println("Sorted distance bus nodes to start point: "+node.getStopId()+" distance: "+node.getDistanceToStartLocation());
        }

        //sort distance from shortest node to end point to furthest node to start point
        Collections.sort(initialStopList, Comparator.comparingDouble(Node::getDistanceToEndLocation));

        // does a deep copy of the sorted array of end location distances
        for (Node node : initialStopList) {
            //Add the object clones
            SortedDistanceEndNodes.add(new Node(node));
            System.out.println("Sorted distance bus nodes to end point: "+node.getStopId()+" distance: "+node.getDistanceToEndLocation());
        }

        // loops through all bus stops to halfway in the list
        // checks to try find two stops that match
        // note increments of 2 cause current plus next stop checked
        // checks A-Z then A-Y then Z-B and does next loop if none match
        for (int i = 0; i < initialStopList.size() / 2; i += 2) {
            // node assignment makes for easier code readability
            Node A = SortedDistanceStartNodes.get(i);
            Node B = SortedDistanceStartNodes.get(i + 1);
            Node Y = SortedDistanceEndNodes.get(i + 1);
            Node Z = SortedDistanceEndNodes.get(i);

            // gets route information for the bus stop
            // currently not stored in db but should be?
            try {
                A.setTransportRoute(busAPIController.getBusStopInfo(A.getStopId()).getTransportRoute());
                B.setTransportRoute(busAPIController.getBusStopInfo(B.getStopId()).getTransportRoute());
                Y.setTransportRoute(busAPIController.getBusStopInfo(Y.getStopId()).getTransportRoute());
                Z.setTransportRoute(busAPIController.getBusStopInfo(Z.getStopId()).getTransportRoute());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // this creates a copy of arraylist A because the retainall method empties array if no matches found
            ArrayList<String> common = new ArrayList<>(A.getTransportRoute());

            //A-Z
            common.retainAll(Z.getTransportRoute());
            //if match found break
            if (common.size() != 0) {
                MatchedIndex = i;
                System.out.println("A routes: "+Arrays.toString(A.getTransportRoute().toArray()));
                System.out.println("A Bus stop: "+A.getStopId());
                System.out.println("Z Bus Stop: "+Arrays.toString(Z.getTransportRoute().toArray()));
                System.out.println("Z routes: "+Z.getStopId());
                //adds the route to the common bus list
                MatchingRouteStops.addAll(common);
                return common;
            } else {
                //reset arraylist
                common = new ArrayList<>(A.getTransportRoute());
            }
            //A-Y
            common.retainAll(Y.getTransportRoute());
            //if match found break
            if (common.size() != 0) {
                MatchedIndex = i;
                System.out.println("A routes: "+Arrays.toString(A.getTransportRoute().toArray()));
                System.out.println("A Bus stop: "+A.getStopId());
                System.out.println("Z Bus Stop: "+Arrays.toString(Z.getTransportRoute().toArray()));
                System.out.println("Z routes: "+Z.getStopId());
                //adds the route to the common bus list
                MatchingRouteStops.addAll(common);
                return common;
            } else {
                //reset arraylist
                common = new ArrayList<>(Y.getTransportRoute());
            }
            //Y-B
            common.retainAll(B.getTransportRoute());
            //if match found break
            if (common.size() != 0) {
                MatchedIndex = i;
                System.out.println("Y routes: "+Arrays.toString(Y.getTransportRoute().toArray()));
                System.out.println("B routes: "+Arrays.toString(B.getTransportRoute().toArray()));
                //adds the route to the common bus list
                MatchingRouteStops.addAll(common);
                return common;
            }

            // this means that no bus stops have matching routes
            if (i == (initialStopList.size() / 2) - 1) {
                return null;
            }
        }

        // shouldnt get here
        return null;
    }

    public ArrayList<String> furtherBusRouteMatches(ArrayList<Node> initialList) {

        //gets the route data for start and end bus stop from match index to count value
        for (int i = MatchedIndex; i<MatchedIndex+CloseNodeCheckAmount; i++) {
            Node currentStartNode = SortedDistanceStartNodes.get(i);
            Node currentEndNode = SortedDistanceEndNodes.get(i);
            try {
                currentStartNode.setTransportRoute(busAPIController.getBusStopInfo(currentStartNode.getStopId()).getTransportRoute());
                currentEndNode.setTransportRoute(busAPIController.getBusStopInfo(currentEndNode.getStopId()).getTransportRoute());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("another loop");
        }

        // both index counts count to x if available
        int outerIndexCount = 0;
        for (int i = MatchedIndex; i<MatchedIndex+CloseNodeCheckAmount; i++) {
            Node endBusStop = SortedDistanceEndNodes.get(i);
            int innerIndexCount = 0;

            //inner loop to check the 5 stops
            for (int j = MatchedIndex; j<MatchedIndex+CloseNodeCheckAmount; j++) {
                Node startBusStop = SortedDistanceStartNodes.get(j);
                // this gets the end stop information
                ArrayList<String> EndStopBusRoutes = endBusStop.getTransportRoute();
                //This gets start stop route information
                ArrayList<String> StartStopBusRoutes = startBusStop.getTransportRoute();

                ArrayList<String> common = new ArrayList<>(StartStopBusRoutes);
                common.retainAll(EndStopBusRoutes);

                //enter if there was a match
                if (common != null && common.size() > 0) {
                    //loop through all the routes and add them to a list
                    for (String route : StartStopBusRoutes) {
                        //check if the route is already in the list
                        if (!MatchingRouteStops.contains(route)) {

                            MatchingRouteStops.add(route);

                        } else {

                            continue;
                        }
                    }
                }
                //checks if 5 bus stops have been cycled through for end nodes inside start nodes
                if (innerIndexCount == CloseNodeCheckAmount) {
                    break;
                } else {
                    //imcrement counter and continue
                    innerIndexCount += 1;
                    continue;
                }
            }

            //checks if 5 bus stops have been cycled through for start nodes
            if (outerIndexCount == CloseNodeCheckAmount) {
                break;
            } else {
                //increment counter and continue
                outerIndexCount += 1;
                continue;
            }
        }
        System.out.println("further amount of routes: " + MatchingRouteStops.size());
        return MatchingRouteStops;
    }


    public ArrayList<Node> minimiseNodeList(ArrayList<Node> initialStopList,LatLng startLocation, LatLng endLocation) {
        ArrayList<Node> finalOutputList = new ArrayList<>();
        // go through the routes and get all matching stops for that routes
        //for (String matchedRoute : MatchingRouteStops) {
            try {
                // call the bus api to get all bus stops for the route
                System.out.println("Chosen Route was: "+MatchingRouteStops.get(4));
                BusRouteList routeList = busAPIController.getRouteInformation(MatchingRouteStops.get(4));
                ArrayList<BusRoute> busStopRouteList = new ArrayList<>();
                for (BusRoute route : routeList.getBusRouteList()) {
                    Node StartStopRoute = route.getBusStopList().get(0);
                    Node FinalStopRoute = route.getBusStopList().get(route.getBusStopList().size() - 1);

                    double DistanceToStartFromBoxStart = distanceTo(StartStopRoute.getLatitude(), StartStopRoute.getLongitudue(), startLocation.lat, startLocation.lng, "K");
                    double DistanceToStartFromBoxEnd = distanceTo(StartStopRoute.getLatitude(), StartStopRoute.getLongitudue(), endLocation.lat, endLocation.lng, "K");
                    double DistanceToEndFromBoxStart = distanceTo(FinalStopRoute.getLatitude(), FinalStopRoute.getLongitudue(), startLocation.lat, startLocation.lng, "K");
                    double DistanceToEndFromBoxEnd = distanceTo(FinalStopRoute.getLatitude(), FinalStopRoute.getLongitudue(), endLocation.lat, endLocation.lng, "K");
                    if (DistanceToStartFromBoxStart < DistanceToStartFromBoxEnd && DistanceToEndFromBoxStart > DistanceToEndFromBoxEnd) {
                        busStopRouteList.add(route);
                        System.out.println("user start to bus start"+DistanceToStartFromBoxStart);
                        System.out.println("user end to bus start"+DistanceToStartFromBoxEnd);
                        System.out.println("user start to bus end"+DistanceToEndFromBoxStart);
                        System.out.println("user end to bus end"+DistanceToEndFromBoxEnd);
                        // not sure about the break statement here
                        break;
                    }


                }

                for (BusRoute route : busStopRouteList) {
                    for(Node stop : route.getBusStopList()){
                        System.out.println("unfiltered Bus stop: "+stop.getStopId());
                    }
                    // stream filter the bus stop list from the initial list to get bus stop matches inside the square
                    ArrayList<Node> filteredStopList = (ArrayList) route.getBusStopList().stream().
                            filter(c -> FilterBusStopsByID(c, initialStopList)).
                            collect(Collectors.toList());
                    route.setBusStopList(filteredStopList);
                }

                ArrayList<String> CheckedRoute = new ArrayList<>();
                for (BusRoute route : busStopRouteList) {
                    if(!CheckedRoute.contains(route.getRouteName())){
                        CheckedRoute.add(route.getRouteName());
                        for(Node stop : route.getBusStopList()){
                            System.out.println("Bus stop: "+stop.getStopId());
                        }
                    }else{
                        continue;
                    }
                    ArrayList<Node> filteredStopList = route.getBusStopList();

                    int NodeCounter = 0;
//                    System.out.println(" final node Array size is: " + filteredStopList.size());
                    int filteredListSize = filteredStopList.size() / 2;
                    System.out.println(" divided by 2 is: " + filteredListSize);
                    System.out.println(" divided by 3 is: " + filteredStopList.size() / 3);
                    //add the final nodes to the list
                    double lowestDistanceToStartLocation = 99999999999.0;
                    double currenStopDistanceToStart = 0;
                    Node PreviousNode = null;
                    int stopIndex = 0;
                    for (Node busStop : filteredStopList) {
                        //check if bus stop is already in list

                        if (!finalOutputList.contains(busStop)) {
                            System.out.println("counter value: " + NodeCounter);
                            //add first and last bus stop to list
                            currenStopDistanceToStart = distanceTo(busStop.getLatitude(), busStop.getLongitudue(), startLocation.lat, startLocation.lng, "K");;
                            System.out.println("Current stop: "+busStop.getStopId()+" Distance: "+currenStopDistanceToStart);
                            System.out.println("Current stop lat: "+busStop.getLatitude()+" current stop long: "+busStop.getLongitudue());
                            if(currenStopDistanceToStart<lowestDistanceToStartLocation&&NodeCounter<=(filteredStopList.size()/3)){
                                lowestDistanceToStartLocation=currenStopDistanceToStart;
                                stopIndex=NodeCounter;
                                System.out.println("Here! in node count");
                            }
                            if(NodeCounter==(filteredStopList.size()/3)){
                                  //  finalOutputList.remove(0);
                               //}else {
                                System.out.println("add to arraylist");
                                    finalOutputList.add(filteredStopList.get(stopIndex));
//                                    StopAdded = true;
                               // }
                            }

                            //filteredStopList.get(0).getStopId().equalsIgnoreCase(busStop.getStopId()) ||
                            if (filteredStopList.get(filteredStopList.size() - 1).getStopId().equalsIgnoreCase(busStop.getStopId())) {
                                finalOutputList.add(busStop);
                                System.out.println("added"+busStop.getStopId());
                            }
                            if (filteredListSize == NodeCounter) {
                                finalOutputList.add(busStop);
                                System.out.println("added+1 "+busStop.getStopId());
                                System.out.println(filteredStopList.get(filteredStopList.size() / 2).getStopId());
                                System.out.println(busStop.getStopId());
                            }
                            NodeCounter += 1;


                        }
                    }
                }

                } catch(IOException e){
                    e.printStackTrace();
                }
        //}
        System.out.println("Final minimised list:" + finalOutputList.size());
        for (Node node : finalOutputList) {
            System.out.println("Stop ID:" + node.getStopId());
            System.out.println("Distance to start node:" + node.getDistanceToStartLocation());
        }
        return finalOutputList;
    }

    private static boolean FilterBusStopsByID(Node busStop, ArrayList<Node> busStops) {
        return busStops.stream().
                filter(b -> b.getStopId().equals(busStop.getStopId())).
                findAny().
                isPresent();
    }

    public double distanceTo(double lat1,double lon1,double lat2,double lon2, String unit) {
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