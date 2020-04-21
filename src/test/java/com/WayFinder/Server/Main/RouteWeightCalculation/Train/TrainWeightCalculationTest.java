package com.WayFinder.Server.Main.RouteWeightCalculation.Train;

import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainWeightCalculationTest {

    private TrainWeightCalculation trainWeightCalculationUnderTest;

    @BeforeEach
    void setUp() {
        trainWeightCalculationUnderTest = new TrainWeightCalculation();
    }

    @Test
    void testGetNodeWeight() {
        // Setup
        final Node firstNode = new Node("Name", 0, 1, 50.0, -40.0, 0.0);
        final Node secondNode = new Node("Name", 0, 1, 51.0, -41.0, 0.0);

        // Run the test
        final int result = trainWeightCalculationUnderTest.getNodeWeight(firstNode, secondNode);

        // Verify the results
        assertEquals(3954, result);
    }

    @Test
    void testDistance() {
        // Setup

        // Run the test
        final double result = trainWeightCalculationUnderTest.Distance(50.0, -40.0, 50.0, -41.0, "unit");

        // Verify the results
        assertEquals(44.40986, result, 0.0001);
    }
}
