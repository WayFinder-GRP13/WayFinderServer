package com.WayFinder.Server.Main.Parsers;

import org.json.JSONArray;
import org.json.JSONObject;


public class GoogleDirectionsParser {

    public String ParseBusStop(String googleData) {
        String result = "";
        JSONObject obj = new JSONObject(googleData);
        System.out.println(obj);//routes//overview_polyline//
        JSONArray routes = obj.getJSONArray("routes");//getJSONObject("summary").getJSONObject("legs").getString("overview_polyline");

        for(int i=0;i<routes.length();i++) {
            JSONObject summaryOverview = routes.getJSONObject(i);
            JSONObject overviewPolyline = summaryOverview.getJSONObject("overview_polyline");
            result = overviewPolyline.getString("points");

        }
        return result;
    }
}
