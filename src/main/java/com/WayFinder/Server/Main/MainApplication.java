package com.WayFinder.Server.Main;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.HTTPRequest.HTTPRequest;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.Models.FinalRoute;
import com.WayFinder.Server.Main.Models.FinalRouteList;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.Parsers.GoogleDirectionsParser;
import com.WayFinder.Server.Main.RestController.BusAPIController;
import com.WayFinder.Server.Main.RouteJSONData.RouteJSONData;
import com.WayFinder.Server.Main.RouteWeightCalculation.RouteWeightCalculationManager;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import com.google.gson.Gson;
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
//
//	String JsonRresult = "[{\"overviewPolyline\":\"cltdIhgbe@w@uAHUGKKRHLdAlBbBvCp@nAdCdEBE\",\"destination\":{\"latitude\":53.35611171,\"longitudue\":-6.244846799,\"score\":99,\"distanceToStartLocation\":4019.43240357,\"distanceToEndLocation\":4084.32419699,\"transportType\":1,\"stopId\":\"618\",\"transportRoute\":[\"53\"],\"name\":\"Newcomen Bridge\"},\"origin\":{\"latitude\":53.35768013,\"longitudue\":-6.242709027,\"score\":99,\"distanceToStartLocation\":4219.15887366,\"distanceToEndLocation\":3888.21577961,\"transportType\":1,\"stopId\":\"4384\",\"transportRoute\":[\"53\"],\"name\":\"Bayview Avenue\"}},{\"overviewPolyline\":\"ybtdInube@nAnBM^\",\"destination\":{\"latitude\":53.35572883,\"longitudue\":-6.24570384,\"score\":99,\"distanceToStartLocation\":3936.22103373,\"distanceToEndLocation\":4170.6943314,\"transportType\":1,\"stopId\":\"516\",\"transportRoute\":[\"53\"],\"name\":\"North Strand Road\"},\"origin\":{\"latitude\":53.35611171,\"longitudue\":-6.244846799,\"score\":99,\"distanceToStartLocation\":4019.43240357,\"distanceToEndLocation\":4084.32419699,\"transportType\":1,\"stopId\":\"618\",\"transportRoute\":[\"53\"],\"name\":\"Newcomen Bridge\"}},{\"overviewPolyline\":\"w`tdI~ybe@L_@V^xAnBzAfB~BqDzCqE|EgHZg@\",\"destination\":{\"latitude\":53.35205785,\"longitudue\":-6.243000423,\"score\":99,\"distanceToStartLocation\":4335.75218,\"distanceToEndLocation\":3821.84249407,\"transportType\":1,\"stopId\":\"7709\",\"transportRoute\":[\"32\"],\"name\":\"Seville Place\"},\"origin\":{\"latitude\":53.35572883,\"longitudue\":-6.24570384,\"score\":99,\"distanceToStartLocation\":3936.22103373,\"distanceToEndLocation\":4170.6943314,\"transportType\":1,\"stopId\":\"516\",\"transportRoute\":[\"53\"],\"name\":\"North Strand Road\"}},{\"overviewPolyline\":\"eisdIbjbe@Xa@p@gA\",\"destination\":{\"latitude\":53.35162049,\"longitudue\":-6.2425978,\"score\":99,\"distanceToStartLocation\":4393.30567232,\"distanceToEndLocation\":3773.6951472,\"transportType\":1,\"stopId\":\"7706\",\"transportRoute\":[\"31B\"],\"name\":\"Seville Place\"},\"origin\":{\"latitude\":53.35205785,\"longitudue\":-6.243000423,\"score\":99,\"distanceToStartLocation\":4335.75218,\"distanceToEndLocation\":3821.84249407,\"transportType\":1,\"stopId\":\"7709\",\"transportRoute\":[\"32\"],\"name\":\"Seville Place\"}}]";
//		System.out.println(JsonRresult);
//			//FinalRouteList data = new Gson().fromJson(JsonRresult, FinalRouteList.class);
//		FinalRoute[] enums =new Gson().fromJson(JsonRresult, FinalRoute[].class);
//
//		 Show it.
//		System.out.println(JsonRresult);
////		System.out.println(enums.length);
////		System.out.println(enums[0].getOverviewPolyline());
//
//		ArrayList<FinalRoute> finalRouteList = new ArrayList();
//
//		JSONArray scores = new JSONArray(JsonRresult);
//		for (int i = 0; i < scores.length(); i++) {
//			JSONObject element = scores.getJSONObject(i);
//			System.out.println(element);
//			String PolyLine = element.getString("overviewPolyline");
//			JSONObject destination = element.getJSONObject("destination");
//			Node destNode = new Node(destination.getString("name"),destination.getInt("stopId"),destination.getInt("transportType"),destination.getDouble("latitude"),destination.getDouble("longitudue"),destination.getDouble("score"));
//			JSONObject origin = element.getJSONObject("origin");
//			Node originNode = new Node(origin.getString("name"),origin.getInt("stopId"),origin.getInt("transportType"),origin.getDouble("latitude"),origin.getDouble("longitudue"),origin.getDouble("score"));
//			finalRouteList.add(new FinalRoute(destNode,originNode,PolyLine));
//			SimilarUserScores object = new SimilarUserScores();
//			object.setUser(element.getString("user"));
//			object.setScore(element.getDouble("score"));
//			objects.add(object);
//		}
//		System.out.println(finalRouteList.get(0).getOverviewPolyline());
//		System.out.println(finalRouteList.get(0).getOrigin().getLatitude());
//	}
	}
}
