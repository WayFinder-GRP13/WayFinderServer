package com.WayFinder.Server.Main.Parsers;

import com.WayFinder.Server.Main.Models.Bike;
import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.Models.Position;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusAPIParser {

    public static ArrayList<BusStop> ParseBusStop(String busStopData){
        ArrayList<BusStop> busStopList = new ArrayList<>();
        JSONObject obj = new JSONObject(busStopData);
        System.out.println(obj);
        JSONArray Routedata = obj.getJSONArray("results");

        for (int i = 0; i < Routedata.length(); i++){
            JSONObject jsonobject = Routedata.getJSONObject(i);
            System.out.println(jsonobject.toString());
            BusStop busStop =  new BusStop();

            busStop.setBusStopID(jsonobject.getInt("stopid"));//latitude/longitude/operators/routes
            busStop.setLatitude(jsonobject.getDouble("latitude"));
            busStop.setLongitude(jsonobject.getDouble("longitude"));

            JSONArray operators = jsonobject.getJSONArray("operators");
            for (int x = 0; x < operators.length(); x++){
                JSONObject opps = operators.getJSONObject(i);
                JSONArray routes = opps.getJSONArray("routes");
                for (int y = 0; y < routes.length(); y++){
                    busStop.addToBusRouteList((String)routes.get(y));

                }
            }

            busStopList.add(busStop);
        }
        return busStopList;
    }

    public static BusRoute ParseBusRoute(String busRouteData){
        BusRoute route = new BusRoute();
        JSONObject obj = new JSONObject(busRouteData);

        route.setRouteName(obj.getString("route"));

        JSONArray Routedata = obj.getJSONArray("results");
        //System.out.println(obj);
        for (int i = 0; i < Routedata.length(); i++) {
            JSONObject jsonobject = Routedata.getJSONObject(i);
            JSONArray stops = jsonobject.getJSONArray("stops");
            for (int j = 0; j < stops.length(); j++) {
                BusStop busStop = new BusStop();
                JSONObject stop = stops.getJSONObject(j);
                busStop.setBusStopID(stop.getInt("stopid"));//latitude/longitude/operators/routes
                busStop.setLatitude(stop.getDouble("latitude"));
                busStop.setLongitude(stop.getDouble("longitude"));

                JSONArray operators = stop.getJSONArray("operators");
                for (int x = 0; x < operators.length(); x++) {
                    JSONObject opps = operators.getJSONObject(x);
                    JSONArray routes = opps.getJSONArray("routes");
                    for (int y = 0; y < routes.length(); y++) {
                        busStop.addToBusRouteList(routes.get(y).toString());

                    }
                }
                route.addBusStop(busStop);
            }
        }
        return route;
    }
}
