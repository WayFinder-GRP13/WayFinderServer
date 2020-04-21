package com.WayFinder.Server.Main.NodeCreation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeCreationManagerTest {

    private NodeCreationManager nodeCreationManagerUnderTest;

    @BeforeEach
    void setUp() {
        nodeCreationManagerUnderTest = new NodeCreationManager();
    }

    @Test
    void testDistanceTo() {
        // Setup

        // Run the test
        final double result = nodeCreationManagerUnderTest.distanceTo(0.0, 0.0, 0.0, 0.0, "unit");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testGetAngle() {
        // Setup

        // Run the test
        final double result = nodeCreationManagerUnderTest.getAngle(0.0, 0.0, 0.0, 0.0);

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testGetNodes() {
        // Setup
        final ArrayList<Node> expectedResult = new ArrayList<>(Arrays.asList(new Node("Name", 0, 0, 0.0, 0.0, 0.0)));

        // Run the test
        final ArrayList<Node> result = nodeCreationManagerUnderTest.getNodes(0.0, 0.0, 0.0, 0.0);

        // Verify the results
        assertEquals(0, result.size());
    }
}
