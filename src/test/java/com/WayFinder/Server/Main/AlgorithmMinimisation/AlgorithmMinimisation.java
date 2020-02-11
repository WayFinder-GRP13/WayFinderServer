package com.WayFinder.Server.Main.AlgorithmMinimisation;

import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlgorithmMinimisation {

        NodeCreationManager nodeCreation = new NodeCreationManager();
        NodeMinimisation nodeMinimisation = new NodeMinimisation();

        @Test
        public void testMinimisation(){
            ArrayList<Node> busList = nodeCreation.getNodes(53.355811, -6.228165,53.351816, -6.223656);
            ArrayList<BusStop> minimisedBusList =nodeMinimisation.minimiseBusStops(busList);
            //size
            assertEquals(2,minimisedBusList.size());
            //id of each expected return stop
            assertEquals(2508,minimisedBusList.get(0).getBusStopID());
            assertEquals(2262,minimisedBusList.get(1).getBusStopID());


        }



    }


