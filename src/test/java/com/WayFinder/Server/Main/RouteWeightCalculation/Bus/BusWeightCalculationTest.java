package com.WayFinder.Server.Main.RouteWeightCalculation.Bus;

import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.UserSettings.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusWeightCalculationTest {

    private BusWeightCalculation busWeightCalculationUnderTest;

    @BeforeEach
    void setUp() {
        busWeightCalculationUnderTest = new BusWeightCalculation();
    }

    @Test
    void testGetNodeWeight() {
        // Setup
        final Node firstNode = new Node("Name", 0, 0, 50.0, 40.0, 0.0);
        final Node secondNode = new Node("Name", 0, 0, 51.5, 41.5, 100.0);
        RestAPIRequestInformation request = new RestAPIRequestInformation(1,"test","55","50","60","40",new UserSettings(1,new BusSettings(true,0),new RailSettings(true,0),new CarSettings(true,0),new WalkSettings(true,0),new CycleSettings(true,0),new ScaleSettings(5,5,5)));

        // Run the test
        final int result = busWeightCalculationUnderTest.getNodeWeight(firstNode, secondNode,request);

        // Verify the results
        assertEquals(4934, result);
    }

    @Test
    void testDistance() {
        // Setup

        // Run the test
        final double result = busWeightCalculationUnderTest.Distance(50.0, -40.0, 55.5, -38.2, "unit");

        // Verify the results
        assertEquals(387.34399829088, result, 0.0001);
    }
}
