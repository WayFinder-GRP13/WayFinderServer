package com.WayFinder.Server.Main;

import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		NodeCreationManager NodeCreationManager=new NodeCreationManager();
		NodeCreationManager.getNodes(53.355811, -6.228165,53.351816, -6.223656);
	}

}
