package com.WayFinder.Server.Main.Constants;

import java.util.HashMap;

public class RouteTypes{

    public static String getRouteType(int transportType){
        HashMap<Integer,String> routeTypes = new HashMap<>();
        routeTypes.put(0,"walking");
        routeTypes.put(1,"transit");//bus
        routeTypes.put(2,"transit");//train
        routeTypes.put(3,"bicycling");
        routeTypes.put(4,"driving");//driving
        return routeTypes.get(transportType);

    }

}