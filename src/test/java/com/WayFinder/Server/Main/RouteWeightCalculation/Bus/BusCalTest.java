package com.WayFinder.Server.Main.RouteWeightCalculation.Bus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusCalTest {

    private BusCal busCalUnderTest;

    @BeforeEach
    void setUp() {
        busCalUnderTest = new BusCal();
    }

    @Test
    void testBusWeight() {
        // Setup

        // Run the test
        final double result = busCalUnderTest.BusWeight(10.0);

        // Verify the results
        assertEquals(8.866666666666667, result, 0.0001);
    }

    @Test
    void testCO2CalBus() {
        // Setup

        // Run the test
        final double result = busCalUnderTest.CO2CalBus(20.0);

        // Verify the results
        assertEquals(3.400000000, result, 0.0001);
    }

    @Test
    void testSpeedBus() {
        // Setup

        // Run the test
        final double result = busCalUnderTest.SpeedBus(25.0);

        // Verify the results
        assertEquals(0.41666666666, result, 0.0001);
    }
}
