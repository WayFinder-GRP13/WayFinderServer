package com.WayFinder.Server.Main.Models;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;

public class BusRouteList {

        private String RouteName;
        private ArrayList<BusRoute> busRouteList = new ArrayList<>();

        public String getRouteName() {
            return RouteName;
        }

        public void setRouteName(String routeName) {
            RouteName = routeName;
        }

        public ArrayList<BusRoute> getBusRouteList() {
            return busRouteList;
        }

        public void addBusRoute(BusRoute busRoute) {
            busRouteList.add(busRoute);
        }


}
