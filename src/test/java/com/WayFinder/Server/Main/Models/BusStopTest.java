package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusStopTest {

    private BusStop busStopUnderTest;

    @BeforeEach
    void setUp() {
        busStopUnderTest = new BusStop();
        busStopUnderTest.RouteList = new ArrayList<>(Arrays.asList("value"));
    }

    @Test
    void testGetBusRouteList() {
        // Setup
        final ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("value"));

        // Run the test
        final ArrayList<String> result = busStopUnderTest.getBusRouteList();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddToBusRouteList() {
        // Setup

        // Run the test
        busStopUnderTest.addToBusRouteList("route");

        // Verify the results
    }
}
