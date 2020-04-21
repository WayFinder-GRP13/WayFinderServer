package com.WayFinder.Server.Main.RouteJSONData;

import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteJSONDataTest {

    private RouteJSONData routeJSONDataUnderTest;

    @BeforeEach
    void setUp() {
        routeJSONDataUnderTest = new RouteJSONData();
        routeJSONDataUnderTest.routeTypes = new HashMap<>();
    }

    @Test
    void testGetJSONpath() {
        // Setup
        final LinkedList<Node> pathList = new LinkedList<>(Arrays.asList(new Node("Name", 0, 0, 0.0, 0.0, 0.0)));
        final ArrayList<Edge> edgeList = new ArrayList<>(Arrays.asList(new Edge("id", new Node("Name", 0, 0, 0.0, 0.0, 0.0), new Node("Name", 0, 0, 0.0, 0.0, 0.0), 0, 0)));
        final ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("value"));

        // Run the test
        final ArrayList<String> result = routeJSONDataUnderTest.getJSONpath(pathList, edgeList);

        // Verify the results
        assertEquals(0, result.size());
    }

    @Test
    void testGetJSONpath1() {
        // Setup
        final Node origin = new Node("Name", 0, 0, 0.0, 0.0, 0.0);
        final Node destination = new Node("Name", 0, 0, 0.0, 0.0, 0.0);

        // Run the test
        final String result = routeJSONDataUnderTest.getJSONpath(origin, destination, "mode");

        // Verify the results
        assertEquals("https://maps.googleapis.com/maps/api/directions/json?origin=0.0,0.0&destination=0.0,0.0&mode=mode&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE", result);
    }

    @Test
    void testParseAPIRequest() {
        // Setup

        // Run the test
        final int result = routeJSONDataUnderTest.ParseAPIRequest("apiRequest");

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testGetNodeFromString() {
        // Setup
        final LinkedList<Node> finalPath = new LinkedList<>(Arrays.asList(new Node("Name", 0, 0, 0.0, 0.0, 0.0)));
        final Node expectedResult = new Node("Name", 0, 0, 0.0, 0.0, 0.0);

        // Run the test
        final Node result = routeJSONDataUnderTest.getNodeFromString("https://maps.googleapis.com/maps/api/directions/json?origin=0.0,0.0&destination=0.0,0.0&mode=mode&key=AIzaSyCqCdlPmegML3DEtc7BL9X1RFVsm8lEBbE", finalPath, "nodeType");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
