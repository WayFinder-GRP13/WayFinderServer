package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class EdgeTest {

    @Mock
    private Node mockSource;
    @Mock
    private Node mockDestination;

    private Edge edgeUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        edgeUnderTest = new Edge("id", mockSource, mockDestination, 0, 0);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = edgeUnderTest.toString();

        // Verify the results
        assertEquals("mockSource mockDestination", result);
    }
}
