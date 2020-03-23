package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.Enums.RequestType;
import com.WayFinder.Server.Main.HTTPRequest.HTTPRequest;
import com.WayFinder.Server.Main.Helpers.RequestHelper;
import com.WayFinder.Server.Main.MainServer.MainClass;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import com.WayFinder.Server.Main.Models.*;

import java.util.Comparator;

import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisationManager;
import com.WayFinder.Server.Main.Parsers.GoogleDirectionsParser;
import com.WayFinder.Server.Main.Parsers.LuasAPIParser;
import com.WayFinder.Server.Main.RouteJSONData.RouteJSONData;
import com.WayFinder.Server.Main.RouteWeightCalculation.RouteWeightCalculationManager;
import com.google.maps.model.LatLng;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.parsers.ParserConfigurationException;

@RestController
public class RestAPIController {
    private List<RestAPIRequestInformation> myRestAPIRequestInformation = new ArrayList();
    private final AtomicLong counter = new AtomicLong();
    private MainClass mainClass = new MainClass();

    public RestAPIController() {
        // note not sure which one to do

    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        //
        // return Math.acos(Math.sin(StartLat*(180/Math.PI)) * Math.sin(StartLat) +
        // Math.cos(StartLat*(180/Math.PI)) * Math.cos(EndLng*(180/Math.PI)) *
        // Math.cos(StartLng*(180/Math.PI) - EndLat*(180/Math.PI))) * radius;
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        }
        return dist;
    }

    @GetMapping(value = "/health/iamalive")
    public ResponseEntity iamalive() {
        return ResponseEntity.ok("Server is running");
    }

    // Read
    @GetMapping(value = "/")
    public ResponseEntity index(@RequestParam(value = "name") String name) {
        RestAPIRequestInformation itemToReturn = null;
        System.out.println(name);
        for (RestAPIRequestInformation object : myRestAPIRequestInformation) {
            System.out.println(object.getName());
            if (object.getName().contains(name)) {
                itemToReturn = object;
            }
        }
        return ResponseEntity.ok(itemToReturn);
    }

    @GetMapping(value = "/user")
    public ResponseEntity getUser(@RequestParam(value = "id") int id) {
        RestAPIRequestInformation itemToReturn = null;
        for (RestAPIRequestInformation user : myRestAPIRequestInformation) {
            if (user.getId() == id)
                itemToReturn = user;
        }
        return ResponseEntity.ok(itemToReturn);
    }

    @PostMapping(value = "/route")
    public ResponseEntity getRoute(@RequestBody RestAPIRequestInformation request) {
        ArrayList<FinalRoute> result = new ArrayList<>();

        // bad request check
        if (!RequestHelper.isValidRequest(request, RequestType.ROUTE)) {
            return new ResponseEntity<>("Some request parameters are missing", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Success");
        mainClass.runRequest(request);
        myRestAPIRequestInformation.add(request);

        NodeCreationManager NodeCreationManager=new NodeCreationManager();
        ArrayList<Node> busStopList = NodeCreationManager.getNodes(request.getStartLocation().lat, request.getStartLocation().lng,request.getEndLocation().lat, request.getEndLocation().lng);

        NodeMinimisationManager nodeMinimisation = new NodeMinimisationManager();
        ArrayList<Node> BusStopsNodes = nodeMinimisation.minimiseNodes(busStopList, new LatLng(request.getStartLocation().lat, request.getStartLocation().lng),new LatLng(request.getEndLocation().lat, request.getEndLocation().lng));

        System.out.println("Bus stop list size: "+BusStopsNodes.size());

        RouteWeightCalculationManager routeWeightCalculationManager= new RouteWeightCalculationManager();
        ArrayList<Edge> edgeList = routeWeightCalculationManager.calculateRouteWeights(BusStopsNodes);

        System.out.println("final edge list size: "+edgeList.size());
        DijkstraAlgorithmManager runNodeGraph = new DijkstraAlgorithmManager();
        LinkedList<Node> finalPath = runNodeGraph.ExecuteAlgorithm(BusStopsNodes,edgeList);


        //add users start and finish location to the list
        // first position
        finalPath.add(0,new Node("StartLocation",0,0,request.getStartLocation().lat,request.getStartLocation().lng,99.9));
        edgeList.add(0,new Edge("start",finalPath.get(0),finalPath.get(1),0,0));
        // end location
        finalPath.add(new Node("EndLocation",99999,0,request.getEndLocation().lat,request.getEndLocation().lng,99.9));
        edgeList.add(new Edge("end",finalPath.get(finalPath.size()-2),finalPath.get(finalPath.size()-1),0,0));

        for (Node node : finalPath) {
            System.out.println("Final Path:"+node.getStopId());
        }

        RouteJSONData routeJSONData = new RouteJSONData();
        ArrayList<String> apiRequest = routeJSONData.getJSONpath(finalPath,edgeList);

        for (String requestAPI : apiRequest) {
            System.out.println("API Request:"+requestAPI);
        }

        HTTPRequest httpRequest = new HTTPRequest();
        GoogleDirectionsParser googleDirectionsParser= new GoogleDirectionsParser();


        int index = 0;
        for (int i=0;i<apiRequest.size();i++) {
            String APIRequest = apiRequest.get(i);
            int transportType = edgeList.get(i).getTransportType();

            System.out.println(request);
            String response = httpRequest.sendHTTPRequest(APIRequest);
            //System.out.println(response);
            String polyLine = googleDirectionsParser.ParseBusStop(response);

            FinalRoute finalRoutePoint = new FinalRoute(finalPath.get(index),finalPath.get(index+1),polyLine,transportType);
            result.add(finalRoutePoint);
            index+=1;
        }

        for(FinalRoute obj : result){
            System.out.println(obj.getOrigin().getStopId());
            System.out.println(obj.getDestination().getStopId());
            System.out.println(obj.getOverviewPolyline());
            System.out.println("Route type: "+obj.getRouteType());
        }



        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/luasroute")
    public ResponseEntity getLuasRoute(@RequestBody RestAPIRequestInformation request) {
        ArrayList<FinalRoute> result = new ArrayList<>();

        // bad request check
        if (!RequestHelper.isValidRequest(request, RequestType.ROUTE)) {
            return new ResponseEntity<>("Some request parameters are missing", HttpStatus.BAD_REQUEST);
        }

        // myRestAPIRequestInformation.add(request);
        ArrayList<LuasLine> luasLines = getLuasData();
        ArrayList<LuasStop> redLineStopList = new ArrayList<LuasStop>();
        ArrayList<LuasStop> greenLineStopList = new ArrayList<LuasStop>();
        ArrayList<LuasInfo> nearestRedLineStartPointInfo = new ArrayList<LuasInfo>();
        ArrayList<LuasInfo> nearestRedLineEndPointInfo = new ArrayList<LuasInfo>();
        ArrayList<LuasInfo> nearestGreenLineStartPointInfo = new ArrayList<LuasInfo>();
        ArrayList<LuasInfo> nearestGreenLineEndPointInfo = new ArrayList<LuasInfo>();
        // list for storing optimum luas line coordinates
        ArrayList<LuasStop> finalLuasStops = new ArrayList<LuasStop>();
        // Adding luas stops separately to red and green
        redLineStopList.addAll(luasLines.get(0).Stops);
        greenLineStopList.addAll(luasLines.get(1).Stops);

        System.out.println("Size of Red luas Lines" + redLineStopList.size());
        // Find nearest stop from start location for red luas line
        for (int i = 0; i < redLineStopList.size(); i++) {
            LuasInfo info = new LuasInfo();
            info.setDistance(getDistance(request.getStartLocation().lat, request.getStartLocation().lng,
                    redLineStopList.get(i).Latitude, redLineStopList.get(i).Longitude, "K"));
            info.IndexInfo = i;
            info.LuasLine = "Red";
            nearestRedLineStartPointInfo.add(info);
        }
        Collections.sort(nearestRedLineStartPointInfo, Comparator.comparingDouble(LuasInfo::getDistance));
        // Find nearest stop from end location for red luas line
        for (int i = 0; i < redLineStopList.size(); i++) {
            LuasInfo info = new LuasInfo();
            info.setDistance(getDistance(request.getEndLocation().lat, request.getEndLocation().lng,
                    redLineStopList.get(i).Latitude, redLineStopList.get(i).Longitude, "K"));
            info.IndexInfo = i;
            info.LuasLine = "Red";
            nearestRedLineEndPointInfo.add(info);
        }
        Collections.sort(nearestRedLineEndPointInfo, Comparator.comparingDouble(LuasInfo::getDistance));
        // Find nearest stop from start location for Green luas line
        for (int i = 0; i < greenLineStopList.size(); i++) {
            LuasInfo info = new LuasInfo();
            info.setDistance(getDistance(request.getStartLocation().lat, request.getStartLocation().lng,
                    greenLineStopList.get(i).Latitude, greenLineStopList.get(i).Longitude, "K"));
            info.IndexInfo = i;
            info.LuasLine = "Green";
            nearestGreenLineStartPointInfo.add(info);
        }
        Collections.sort(nearestGreenLineStartPointInfo, Comparator.comparingDouble(LuasInfo::getDistance));
        // Find nearest stop from end location for Green luas line
        for (int i = 0; i < greenLineStopList.size(); i++) {
            LuasInfo info = new LuasInfo();
            info.setDistance(getDistance(request.getEndLocation().lat, request.getEndLocation().lng,
                    greenLineStopList.get(i).Latitude, greenLineStopList.get(i).Longitude, "K"));
            info.IndexInfo = i;
            info.LuasLine = "Green";
            nearestGreenLineEndPointInfo.add(info);
        }
        Collections.sort(nearestGreenLineEndPointInfo, Comparator.comparingDouble(LuasInfo::getDistance));
        // Find the index of the start and end stops in redLineStopList and get the
        // stops between them
        System.out.println("RED LUAS LINE");
        int redLineStartingIndex = nearestRedLineStartPointInfo.get(0).IndexInfo;
        String redLineStartLuasStop = redLineStopList.get(redLineStartingIndex).Pronunciation;
        System.out.println("Starting Index and Stop: " + redLineStartingIndex + " " + redLineStartLuasStop);
        int redLineEndingIndex = nearestRedLineEndPointInfo.get(0).IndexInfo;
        String redLineEndLuasStop = redLineStopList.get(redLineEndingIndex).Pronunciation;
        System.out.println("Ending Index and Stop: " + redLineEndingIndex + " " + redLineEndLuasStop);
        for (int i = redLineStartingIndex; i <= redLineEndingIndex; i++) {
            System.out.println(redLineStopList.get(i).Pronunciation);
        }
        // Find the index of the start and end stops in greenLineStopList and get the
        // stops between them
        System.out.println("GREEN LUAS LINE");
        int greenLineStartingIndex = nearestGreenLineStartPointInfo.get(0).IndexInfo;
        String greenLineStartLuasStop = greenLineStopList.get(greenLineStartingIndex).Pronunciation;
        System.out.println("Starting Index and Stop: " + greenLineStartingIndex + " " + greenLineStartLuasStop);
        int greenLineEndingIndex = nearestGreenLineEndPointInfo.get(0).IndexInfo;
        String greenLineEndLuasStop = greenLineStopList.get(greenLineEndingIndex).Pronunciation;
        System.out.println("Ending Index and Stop: " + greenLineEndingIndex + " " + greenLineEndLuasStop);
        for (int i = greenLineStartingIndex; i <= greenLineEndingIndex; i++) {
            System.out.println(greenLineStopList.get(i).Pronunciation);
        }
        // Find the shortest distant luas stop from start location to both the luas
        // lines
        System.out.println("OPTIMAL LUAS ROUTE");
        if (nearestRedLineStartPointInfo.get(0).getDistance() < nearestGreenLineStartPointInfo.get(0).getDistance()) {
            for (int i = redLineStartingIndex; i <= redLineEndingIndex; i++) {
                LuasStop luasStop = new LuasStop();
                luasStop.Latitude = redLineStopList.get(i).Latitude;
                luasStop.Longitude = redLineStopList.get(i).Longitude;
                luasStop.Pronunciation = redLineStopList.get(i).Pronunciation;
                finalLuasStops.add(luasStop);
            }
        } else {
            for (int i = greenLineStartingIndex; i <= greenLineEndingIndex; i++) {
                LuasStop luasStop = new LuasStop();
                luasStop.Latitude = greenLineStopList.get(i).Latitude;
                luasStop.Longitude = greenLineStopList.get(i).Longitude;
                luasStop.Pronunciation = greenLineStopList.get(i).Pronunciation;
                finalLuasStops.add(luasStop);
            }
        }
        // The coordinates for luas stops along the optimal path
        System.out.println("Luas Coordinates: ");
        HTTPRequest httpRequest = new HTTPRequest();
        GoogleDirectionsParser googleDirectionsParser = new GoogleDirectionsParser();

        RouteJSONData routeJSONData = new RouteJSONData();
        

        for (int i = 0; i < finalLuasStops.size()-1; i++) {
            System.out.println("Stop :- " + finalLuasStops.get(i).Pronunciation);
            Node origin = new Node();
            origin.setName(finalLuasStops.get(i).Pronunciation);
            origin.setLatitude(finalLuasStops.get(i).Latitude);
            origin.setLongitudue(finalLuasStops.get(i).Longitude);

            Node destination = new Node();
            destination.setName(finalLuasStops.get(i+1).Pronunciation);
            destination.setLatitude(finalLuasStops.get(i+1).Latitude);
            destination.setLongitudue(finalLuasStops.get(i+1).Longitude);

            String apiRequest = routeJSONData.getJSONpath(origin, destination, "transit");
            String response = httpRequest.sendHTTPRequest(apiRequest);
            // System.out.println(response);
            String polyLine = googleDirectionsParser.ParseBusStop(response);

            FinalRoute finalRoutePoint = new FinalRoute(origin, destination, polyLine,2);
            result.add(finalRoutePoint);
        }

        // 1. find the nearest luas stops from start and end location
        // 2. store the index in the array of those locations
        // 3. get the stops between start and end locations
        // 4. send to google api and get polyline between each points
        // 5. send to the app

        return ResponseEntity.ok(result);
    }

    private ArrayList<LuasLine> getLuasData() {
        try {
            URL url = new URL("https://luasforecasts.rpa.ie/xml/get.ashx?action=stops&encrypt=false");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/xml");

            int responseCode = con.getResponseCode();
            String readLine = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                return LuasAPIParser.Parse(response.toString());
            } else {
                return new ArrayList<LuasLine>();
            }
        } catch (Exception e) {
            return new ArrayList<LuasLine>();
        }
    }

    @PostMapping(value = "/railapi")
    public ResponseEntity railResponse(@RequestBody RestAPIRequestInformation request) {
        if (request != null) {
            System.out.println("Success");
        }

        return ResponseEntity.ok(myRestAPIRequestInformation);
    }

    // Update/Replace
    @PutMapping(value = "/")
    public ResponseEntity updateUserList(@RequestParam(value = "name") String name,
            @RequestParam(value = "id") Long id) {
        myRestAPIRequestInformation.forEach(RestAPIRequestInformation -> {
            if (RestAPIRequestInformation.getId() == id) {
                RestAPIRequestInformation.setName(name);
            }
        });
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity removeUserList(@RequestParam(value = "userId") Long userId) {
        RestAPIRequestInformation itemToRemove = null;
        for (RestAPIRequestInformation user : myRestAPIRequestInformation) {
            if (user.getId() == userId)
                itemToRemove = user;
        }
        myRestAPIRequestInformation.remove(itemToRemove);
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }
}
