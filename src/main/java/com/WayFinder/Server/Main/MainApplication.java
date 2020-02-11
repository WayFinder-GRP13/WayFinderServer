package com.WayFinder.Server.Main;

import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.RestController.BusAPIController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		//test code
		NodeCreationManager NodeCreationManager=new NodeCreationManager();
		ArrayList<Node> busStopList = NodeCreationManager.getNodes(53.355811, -6.228165,53.351816, -6.223656);

		NodeMinimisation nodeMinimisation = new NodeMinimisation();
		nodeMinimisation.minimiseBusStops(busStopList);
//		BusAPIController busAPI = new BusAPIController();
//		try {
//			//busAPI.getBusStopInfo(147);
//			busAPI.getRouteInformation(4);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
