package com.WayFinder.Server.Main.Parsers;

import com.WayFinder.Server.Main.Models.*;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusAPIParser {

    public static Node ParseBusStop(String busStopData){
        Node busStop = new Node();
        JSONObject obj = new JSONObject(busStopData);
        JSONArray Routedata = obj.getJSONArray("results");

        for (int i = 0; i < Routedata.length(); i++){
            JSONObject jsonobject = Routedata.getJSONObject(i);
            busStop =  new Node();

            busStop.setStopId(jsonobject.getString("stopid"));//latitude/longitude/operators/routes
            busStop.setLatitude(jsonobject.getDouble("latitude"));
            busStop.setLongitudue(jsonobject.getDouble("longitude"));

            JSONArray operators = jsonobject.getJSONArray("operators");
            for (int x = 0; x < operators.length(); x++){
                JSONObject opps = operators.getJSONObject(i);
                JSONArray routes = opps.getJSONArray("routes");
                for (int y = 0; y < routes.length(); y++){
                    busStop.addTransportRouteToList((String)routes.get(y));

                }
            }

        }
        return busStop;
    }

    public static BusRouteList ParseBusRoute(String busRouteData){
        BusRouteList busRouteList = new BusRouteList();
        JSONObject obj = new JSONObject(busRouteData);

        JSONArray Routedata = obj.getJSONArray("results");
        //System.out.println(obj);
        for (int i = 0; i < Routedata.length(); i++) {
            BusRoute route = new BusRoute();
            route.setRouteName(obj.getString("route"));
            busRouteList.addBusRoute(route);
            JSONObject jsonobject = Routedata.getJSONObject(i);
            JSONArray stops = jsonobject.getJSONArray("stops");
            System.out.println("looped extra route");
            for (int j = 0; j < stops.length(); j++) {
                Node busStop = new Node();
                JSONObject stop = stops.getJSONObject(j);
                busStop.setStopId(stop.getString("stopid"));//latitude/longitude/operators/routes
                busStop.setLatitude(stop.getDouble("latitude"));
                busStop.setLongitudue(stop.getDouble("longitude"));
                busStop.setTransportType(1);

                JSONArray operators = stop.getJSONArray("operators");
                for (int x = 0; x < operators.length(); x++) {
                    JSONObject opps = operators.getJSONObject(x);
                    JSONArray routes = opps.getJSONArray("routes");
                    for (int y = 0; y < routes.length(); y++) {
                        busStop.addTransportRouteToList(routes.get(y).toString());

                    }
                }
                route.addBusStop(busStop);
            }
        }
        return busRouteList;
    }


    public static BusRoute ParseBusRouteInOneList(String busRouteData){
        BusRoute route = new BusRoute();
        JSONObject obj = new JSONObject(busRouteData);

        JSONArray Routedata = obj.getJSONArray("results");
        //System.out.println(obj);
        for (int i = 0; i < Routedata.length(); i++) {
            route.setRouteName(obj.getString("route"));
            JSONObject jsonobject = Routedata.getJSONObject(i);
            JSONArray stops = jsonobject.getJSONArray("stops");
            System.out.println("looped extra route");
            for (int j = 0; j < stops.length(); j++) {
                Node busStop = new Node();
                JSONObject stop = stops.getJSONObject(j);
                busStop.setStopId(stop.getString("stopid"));//latitude/longitude/operators/routes
                busStop.setLatitude(stop.getDouble("latitude"));
                busStop.setLongitudue(stop.getDouble("longitude"));
                busStop.setTransportType(1);

                JSONArray operators = stop.getJSONArray("operators");
                for (int x = 0; x < operators.length(); x++) {
                    JSONObject opps = operators.getJSONObject(x);
                    JSONArray routes = opps.getJSONArray("routes");
                    for (int y = 0; y < routes.length(); y++) {
                        busStop.addTransportRouteToList(routes.get(y).toString());

                    }
                }
                route.addBusStop(busStop);
            }
        }
        return route;
    }

}
