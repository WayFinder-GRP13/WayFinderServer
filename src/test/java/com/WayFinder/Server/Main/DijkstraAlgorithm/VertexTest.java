package com.WayFinder.Server.Main.DijkstraAlgorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VertexTest {

    private Vertex vertexUnderTest;

    @BeforeEach
    void setUp() {
        vertexUnderTest = new Vertex("555", "dundrum", 0, 0.0, 0.0);
    }

    @Test
    void testGetId() {
        // Setup

        // Run the test
        final String result = vertexUnderTest.getId();

        // Verify the results
        assertEquals("555", result);
    }

    @Test
    void testGetName() {
        // Setup

        // Run the test
        final String result = vertexUnderTest.getName();

        // Verify the results
        assertEquals("dundrum", result);
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = vertexUnderTest.hashCode();

        // Verify the results
        assertEquals(52660, result);
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = vertexUnderTest.equals(vertexUnderTest);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = vertexUnderTest.toString();

        // Verify the results
        assertEquals("dundrum", result);
    }
}
