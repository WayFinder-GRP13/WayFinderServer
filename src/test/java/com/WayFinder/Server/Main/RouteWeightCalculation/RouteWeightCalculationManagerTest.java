package com.WayFinder.Server.Main.RouteWeightCalculation;

import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteWeightCalculationManagerTest {

    private RouteWeightCalculationManager routeWeightCalculationManagerUnderTest;

    @BeforeEach
    void setUp() {
        routeWeightCalculationManagerUnderTest = new RouteWeightCalculationManager();
    }

    @Test
    void testCalculateRouteWeights() {
        // Setup
        final ArrayList<Node> NodeList = new ArrayList<>(Arrays.asList(new Node("Name", 0, 2, 50.0, 50.0, 0.0),new Node("Name", 1, 2, 50.0, 40.0, 0.0),new Node("Name", 2, 0, 40.0, 40.0, 0.0)));

        // Run the test
        final ArrayList<Edge> result = routeWeightCalculationManagerUnderTest.calculateRouteWeights(NodeList, request);

        assertEquals(3500,result.get(0).getWeight());
        assertEquals(5449,result.get(1).getWeight());

        // Verify the results
    }
}
