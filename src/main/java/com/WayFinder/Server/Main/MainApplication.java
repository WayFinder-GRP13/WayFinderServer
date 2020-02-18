package com.WayFinder.Server.Main;

import com.WayFinder.Server.Main.DijkstraAlgorithm.DijkstraAlgorithmManager;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.RestController.BusAPIController;
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
		NodeCreationManager NodeCreationManager=new NodeCreationManager();
		ArrayList<Node> busStopList = NodeCreationManager.getNodes(53.364084, -6.280115,53.349355, -6.208735);

		NodeMinimisation nodeMinimisation = new NodeMinimisation();
		ArrayList<Node> BusStopsNodes = nodeMinimisation.minimiseBusStops(busStopList);

		System.out.println("Bus stop list size: "+BusStopsNodes.size());

		RouteWeightCalculationManager routeWeightCalculationManager= new RouteWeightCalculationManager();
		ArrayList<Edge> edgeList = routeWeightCalculationManager.calculateRouteWeights(BusStopsNodes);

		System.out.println("final edge list size: "+edgeList.size());
		DijkstraAlgorithmManager runNodeGraph = new DijkstraAlgorithmManager();
		LinkedList<Node> finalPath = runNodeGraph.ExecuteAlgorithm(BusStopsNodes,edgeList);

		for (Node node : finalPath) {
			System.out.println(node.getStopId());
		}
	}

}
