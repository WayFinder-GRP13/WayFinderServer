package com.WayFinder.Server.Main.AlgorithmPart1Tests;

import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.NodeCreation.NodeCreationManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class AlgorithmPart1Test {
    NodeCreationManager nodeCreation = new NodeCreationManager();

    @Test
    public void testRangeResponseCorrect(){
       // ArrayList<Node> busList = nodeCreation.getNodes(53.355811, -6.228165,53.351816, -6.223656);
//        Node busStop = busList.get(0);
//        String stopName = busStop.getName();
//        String stopID = busStop.getStopId();
//        double latitude = busStop.getLatitude();
//        double longitude =busStop.getLongitudue();
//        assertEquals("Merchants Road",stopName.toString());
//        assertEquals("2262",stopID);
//        assertEquals(latitude,53.353522900000002,.001);
//        assertEquals(longitude,-6.226595602000000,.001);

    }

    @Test
    public void testDistanceMethod(){
        double distance = nodeCreation.distanceTo(53.355811, -6.228165,53.351816, -6.223656,"K");
        // allow 10 meters discrepancy
        assertEquals(0.5355956316606524,distance,0.01);
    }

    @Test
    public void testRangeResponseRequest(){
        // get dublin bus nodes
//        ArrayList<Node> busList = nodeCreation.getNodes(53.355811, -6.228165,53.351816, -6.223656);
//        assertTrue(busList.isEmpty()==false);
    }

    @Test
    public void testAngleCalculation(){
        double angle = nodeCreation.getAngle(53.355811, -6.228165,53.351816, -6.223656);
        // allow .1 degrees in angle discrepancy
        assertEquals(-56.034440634144005,angle,0.1);
    }



}
