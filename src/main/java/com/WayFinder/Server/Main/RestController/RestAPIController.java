package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.Enums.RequestType;
import com.WayFinder.Server.Main.HTTPRequest.HTTPRequest;
import com.WayFinder.Server.Main.Helpers.RequestHelper;
import com.WayFinder.Server.Main.MainServer.MainClass;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import com.WayFinder.Server.Main.Models.FinalRoute;
import com.WayFinder.Server.Main.Models.LuasLine;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisationManager;
import com.WayFinder.Server.Main.Parsers.GoogleDirectionsParser;
import com.WayFinder.Server.Main.Parsers.LuasAPIParser;
import com.WayFinder.Server.Main.RouteJSONData.RouteJSONData;
import com.WayFinder.Server.Main.RouteWeightCalculation.RouteWeightCalculationManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

        myRestAPIRequestInformation.add(request);

        NodeCreationManager NodeCreationManager = new NodeCreationManager();
        ArrayList<Node> busStopList = NodeCreationManager.getNodes(request.getStartLocation().lat,
                request.getStartLocation().lng, request.getEndLocation().lat, request.getEndLocation().lng);

        NodeMinimisationManager nodeMinimisation = new NodeMinimisationManager();
        ArrayList<Node> BusStopsNodes = nodeMinimisation.minimiseNodes(busStopList);

        System.out.println("Bus stop list size: " + BusStopsNodes.size());

        RouteWeightCalculationManager routeWeightCalculationManager = new RouteWeightCalculationManager();
        ArrayList<Edge> edgeList = routeWeightCalculationManager.calculateRouteWeights(BusStopsNodes);

        System.out.println("final edge list size: " + edgeList.size());
        DijkstraAlgorithmManager runNodeGraph = new DijkstraAlgorithmManager();
        LinkedList<Node> finalPath = runNodeGraph.ExecuteAlgorithm(BusStopsNodes, edgeList);

        for (Node node : finalPath) {
            System.out.println(node.getStopId());
        }

        RouteJSONData routeJSONData = new RouteJSONData();
        ArrayList<String> apiRequest = routeJSONData.getJSONpath(finalPath, edgeList);

        HTTPRequest httpRequest = new HTTPRequest();
        GoogleDirectionsParser googleDirectionsParser = new GoogleDirectionsParser();

        int index = 0;
        for (String APIRequest : apiRequest) {
            System.out.println(request);
            String response = httpRequest.sendHTTPRequest(APIRequest);
            // System.out.println(response);
            String polyLine = googleDirectionsParser.ParseBusStop(response);

            FinalRoute finalRoutePoint = new FinalRoute(finalPath.get(index), finalPath.get(index + 1), polyLine);
            result.add(finalRoutePoint);
            index += 1;
        }

        // for (FinalRoute obj : result) {
        // System.out.println(obj.getOrigin().getStopId());
        // System.out.println(obj.getDestination().getStopId());
        // System.out.println(obj.getOverviewPolyline());
        // }

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
        //Iterator i = aList.iterator();
        System.out.println("The ArrayList elements are:");
        for(int i=0;i<luasLines.size();i++) {
            System.out.println(luasLines.get(i).LineName+"\n");
        }
        //1. find the nearest luas stops from start and end location
        //2. store the index in the array of those locations
        //3. get the stops between start and end locations
        //4. send to google api and get polyline between each points
        //5. send to the app

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
