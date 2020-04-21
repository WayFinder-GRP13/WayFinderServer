package com.WayFinder.Server.Main.RouteWeightCalculation.Cycle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CycleCalTest {

    private CycleCal cycleCalUnderTest;

    @BeforeEach
    void setUp() {
        cycleCalUnderTest = new CycleCal();
    }

    @Test
    void testCycleWeight() {
        // Setup

        // Run the test
        final double result = cycleCalUnderTest.CycleWeight(20.0);

        // Verify the results
        assertEquals(1.1290909, result, 0.0001);
    }

    @Test
    void testCO2CalCycle() {
        // Setup

        // Run the test
        final double result = cycleCalUnderTest.CO2CalCycle(30.0);

        // Verify the results
        assertEquals(0.32999999, result, 0.0001);
    }

    @Test
    void testCycleTime() {
        // Setup

        // Run the test
        final double result = cycleCalUnderTest.CycleTime(30.0);

        // Verify the results
        assertEquals(1.363636, result, 0.0001);
    }

    @Test
    void testCycleCost() {
        // Setup

        // Run the test
        final double result = cycleCalUnderTest.CycleCost(20.0);

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testMoreThan4Hour() {
        // Setup

        // Run the test
        final double result = cycleCalUnderTest.moreThan4Hour(700);

        // Verify the results
        assertEquals(348.0, result, 0.0001);
    }
}
