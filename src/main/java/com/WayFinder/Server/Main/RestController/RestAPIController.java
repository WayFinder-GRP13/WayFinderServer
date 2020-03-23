package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.HTTPRequest.HTTPRequest;
import com.WayFinder.Server.Main.MainServer.MainClass;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import com.WayFinder.Server.Main.Models.FinalRoute;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisationManager;
import com.WayFinder.Server.Main.Parsers.GoogleDirectionsParser;
import com.WayFinder.Server.Main.RouteJSONData.RouteJSONData;
import com.WayFinder.Server.Main.RouteWeightCalculation.RouteWeightCalculationManager;
import com.google.maps.model.LatLng;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestAPIController {
    private List<RestAPIRequestInformation> myRestAPIRequestInformation = new ArrayList();
    private final AtomicLong counter = new AtomicLong();
    private MainClass mainClass = new MainClass();

    public RestAPIController(){
        //note not sure which one to do




    }
    //Read
    @GetMapping(value = "/")
    public ResponseEntity index(@RequestParam(value="name") String name) {
        RestAPIRequestInformation itemToReturn = null;
        System.out.println(name);
        for (RestAPIRequestInformation object : myRestAPIRequestInformation){
            System.out.println(object.getName());
            if(object.getName().contains(name)){
                itemToReturn = object;
            }
        }
        return ResponseEntity.ok(itemToReturn);
    }
    @GetMapping(value = "/bucket")
    public ResponseEntity getUser(@RequestParam(value="id") int id) {
        RestAPIRequestInformation itemToReturn = null;
        for(RestAPIRequestInformation user : myRestAPIRequestInformation){
            if(user.getId() == id)
                itemToReturn = user;
        }
        return ResponseEntity.ok(itemToReturn);
    }

    @PostMapping(value = "/")
    public ResponseEntity addToUserList(@RequestBody RestAPIRequestInformation request) {
        ArrayList<FinalRoute> result = new ArrayList<>();
        if(request!=null){
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

        }


        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/railapi")
    public ResponseEntity railResponse(@RequestBody RestAPIRequestInformation request) {
        if(request!=null){
            System.out.println("Success");


        }

        return ResponseEntity.ok(myRestAPIRequestInformation);
    }

    //Update/Replace
    @PutMapping(value = "/")
    public ResponseEntity updateUserList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myRestAPIRequestInformation.forEach(RestAPIRequestInformation ->  {
            if(RestAPIRequestInformation.getId() == id){
                RestAPIRequestInformation.setName(name);
            }
        });
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeUserList(@RequestParam(value="id") Long id) {
        RestAPIRequestInformation itemToRemove = null;
        for(RestAPIRequestInformation user : myRestAPIRequestInformation){
            if(user.getId() == id)
                itemToRemove = user;
        }
        myRestAPIRequestInformation.remove(itemToRemove);
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }



}
