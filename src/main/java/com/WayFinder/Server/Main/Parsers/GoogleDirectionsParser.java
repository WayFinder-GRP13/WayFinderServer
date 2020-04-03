package com.WayFinder.Server.Main.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GoogleDirectionsParser {

    public RouteResponse ParseBusStop(String googleData) {
        String result = "";
        JSONObject obj = new JSONObject(googleData);
        JSONArray routes = obj.getJSONArray("routes");//getJSONObject("summary").getJSONObject("legs").getString("overview_polyline");
        RouteResponse routeResponse = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject summaryOverview = routes.getJSONObject(i);
            JSONObject overviewPolyline = summaryOverview.getJSONObject("overview_polyline");
            result = overviewPolyline.getString("points");
            JSONArray legs = summaryOverview.getJSONArray("legs");
            JSONObject tripDurationJSON = legs.getJSONObject(0).getJSONObject("duration");
            int tripDuration = tripDurationJSON.getInt("value");
            System.out.println("output of trip Duration is: " + tripDuration);
            JSONArray steps = legs.getJSONObject(0).getJSONArray("steps");
            JSONObject transitDetails = null;
            // check if this part was walking or bus
            try {
                transitDetails = steps.getJSONObject(0).getJSONObject("transit_details");
            }catch(JSONException e){
                transitDetails = steps.getJSONObject(1).getJSONObject("transit_details");
            }
            JSONObject line = transitDetails.getJSONObject("line");
            String busRoute = line.getString("short_name");
            System.out.println("output of busNumber is: " + busRoute);
            JSONObject departureTimeJSON = transitDetails.getJSONObject("departure_time");
            String departureTime = departureTimeJSON.getString("text");
            System.out.println("output of departure Time is: " + departureTime);

            routeResponse = new RouteResponse(result, busRoute, departureTime, tripDuration);
            }




        return routeResponse;
    }

    public RouteResponse ParseLuasStopResponse(String googleData) {
        String result = "";
        JSONObject obj = new JSONObject(googleData);
        JSONArray routes = obj.getJSONArray("routes");//getJSONObject("summary").getJSONObject("legs").getString("overview_polyline");
        RouteResponse routeResponse = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject summaryOverview = routes.getJSONObject(i);
            JSONObject overviewPolyline = summaryOverview.getJSONObject("overview_polyline");
            result = overviewPolyline.getString("points");
            JSONArray legs = summaryOverview.getJSONArray("legs");
            JSONObject tripDurationJSON = legs.getJSONObject(0).getJSONObject("duration");
            int tripDuration = tripDurationJSON.getInt("value");
            System.out.println("output of trip Duration is: " + tripDuration);
            JSONArray steps = legs.getJSONObject(0).getJSONArray("steps");
            JSONObject transitDetails = null;
            // check if this part was walking or bus
            try {
                transitDetails = steps.getJSONObject(0).getJSONObject("transit_details");
            }catch(JSONException e){
                transitDetails = steps.getJSONObject(1).getJSONObject("transit_details");
            }
            JSONObject line = transitDetails.getJSONObject("line");
            String busRoute = line.getString("name");
            System.out.println("output of Luas line is: " + busRoute);
            JSONObject departureTimeJSON = transitDetails.getJSONObject("departure_time");
            String departureTime = departureTimeJSON.getString("text");
            System.out.println("output of departure Time is: " + departureTime);

            routeResponse = new RouteResponse(result, busRoute, departureTime, tripDuration);
        }




        return routeResponse;
    }

    public String ParseLuasStop(String response) {
        String result = "";
        JSONObject obj = new JSONObject(response);
        System.out.println(obj);//routes//overview_polyline//
        JSONArray routes = obj.getJSONArray("routes");//getJSONObject("summary").getJSONObject("legs").getString("overview_polyline");

        for (int i = 0; i < routes.length(); i++) {
            JSONObject summaryOverview = routes.getJSONObject(i);
            JSONObject overviewPolyline = summaryOverview.getJSONObject("overview_polyline");
            result = overviewPolyline.getString("points");

        }
        return result;
    }

    public RouteResponse ParseWalking(String response) {
        String result = "";
        JSONObject obj = new JSONObject(response);
        JSONArray routes = obj.getJSONArray("routes");//getJSONObject("summary").getJSONObject("legs").getString("overview_polyline");
        RouteResponse routeResponse = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject summaryOverview = routes.getJSONObject(i);
            JSONObject overviewPolyline = summaryOverview.getJSONObject("overview_polyline");
            result = overviewPolyline.getString("points");
            JSONArray legs = summaryOverview.getJSONArray("legs");
            JSONObject tripDurationJSON = legs.getJSONObject(0).getJSONObject("duration");
            int tripDuration = tripDurationJSON.getInt("value");
            System.out.println("output of trip Duration is: " + tripDuration);

            routeResponse = new RouteResponse(result, "Walking", "", tripDuration);

        }
        return routeResponse;
    }
}

