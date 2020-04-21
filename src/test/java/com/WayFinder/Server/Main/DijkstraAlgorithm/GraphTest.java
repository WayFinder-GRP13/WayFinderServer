package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTest {

    private Graph graphUnderTest;

    @BeforeEach
    void setUp() {
        graphUnderTest = new Graph(Arrays.asList(new Node("Name", 0, 0, 0.0, 0.0, 0.0)), Arrays.asList(new Edge("id", new Node("Name", 0, 0, 0.0, 0.0, 0.0), new Node("Name", 0, 0, 0.0, 0.0, 0.0), 0, 0)));
    }

    @Test
    void testGetVertexes() {
        // Setup
        final List<Node> expectedResult = Arrays.asList(new Node("Name", 0, 0, 0.0, 0.0, 0.0));

        // Run the test
        final List<Node> result = graphUnderTest.getVertexes();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
