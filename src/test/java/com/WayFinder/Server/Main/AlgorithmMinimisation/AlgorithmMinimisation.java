package com.WayFinder.Server.Main.AlgorithmMinimisation;

import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisation;
import com.WayFinder.Server.Main.NodeMinimisation.NodeMinimisationManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlgorithmMinimisation {

        NodeCreationManager nodeCreation = new NodeCreationManager();
        NodeMinimisationManager nodeMinimisation = new NodeMinimisationManager();

        @Test
        public void testMinimisation(){
            ArrayList<Node> busList = nodeCreation.getNodes(53.355811, -6.228165,53.351816, -6.223656);
            ArrayList<Node> minimisedBusList =nodeMinimisation.minimiseNodes(busList);
            //size
            assertEquals(2,minimisedBusList.size());
            //id of each expected return stop
            assertEquals("2508",minimisedBusList.get(0).getStopId());
            assertEquals("2262",minimisedBusList.get(1).getStopId());


        }



    }


