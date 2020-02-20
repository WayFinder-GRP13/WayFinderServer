package com.WayFinder.Server.Main;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.HTTPRequest.HTTPRequest;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.Models.FinalRoute;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.Parsers.GoogleDirectionsParser;
import com.WayFinder.Server.Main.RestController.BusAPIController;
import com.WayFinder.Server.Main.RouteJSONData.RouteJSONData;
import com.WayFinder.Server.Main.RouteWeightCalculation.RouteWeightCalculationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		//test code
//		NodeCreationManager NodeCreationManager=new NodeCreationManager();
//		ArrayList<Node> busStopList = NodeCreationManager.getNodes(53.364084, -6.280115,53.349355, -6.208735);
//
//		NodeMinimisation nodeMinimisation = new NodeMinimisation();
//		ArrayList<Node> BusStopsNodes = nodeMinimisation.minimiseBusStops(busStopList);
//
//		System.out.println("Bus stop list size: "+BusStopsNodes.size());
//
//		RouteWeightCalculationManager routeWeightCalculationManager= new RouteWeightCalculationManager();
//		ArrayList<Edge> edgeList = routeWeightCalculationManager.calculateRouteWeights(BusStopsNodes);
//
//		System.out.println("final edge list size: "+edgeList.size());
//		DijkstraAlgorithmManager runNodeGraph = new DijkstraAlgorithmManager();
//		LinkedList<Node> finalPath = runNodeGraph.ExecuteAlgorithm(BusStopsNodes,edgeList);
//
//		for (Node node : finalPath) {
//			System.out.println(node.getStopId());
//		}
//
//		RouteJSONData routeJSONData = new RouteJSONData();
//		ArrayList<String> apiRequest = routeJSONData.getJSONpath(finalPath,edgeList);
//
//		HTTPRequest httpRequest = new HTTPRequest();
//		GoogleDirectionsParser googleDirectionsParser= new GoogleDirectionsParser();
//
//		ArrayList<FinalRoute> result = new ArrayList<>();
//		int index = 0;
//		for (String request:apiRequest) {
//			System.out.println(request);
//			String response = httpRequest.sendHTTPRequest(request);
//			//System.out.println(response);
//			String polyLine = googleDirectionsParser.ParseBusStop(response);
//
//			FinalRoute finalRoutePoint = new FinalRoute(finalPath.get(index),finalPath.get(index+1),polyLine);
//			result.add(finalRoutePoint);
//			index+=1;
//		}
//
//		for(FinalRoute obj : result){
//			System.out.println(obj.getOrigin().getStopId());
//			System.out.println(obj.getDestination().getStopId());
//			System.out.println(obj.getOverviewPolyline());
//		}

	}

}
