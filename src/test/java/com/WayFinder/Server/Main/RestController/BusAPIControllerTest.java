package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusRouteList;
import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BusAPIControllerTest {

    private BusAPIController busAPIControllerUnderTest;

    @BeforeEach
    void setUp() {
        busAPIControllerUnderTest = new BusAPIController();
    }

    @Test
    void testGetBusStopInfo() throws Exception {
        // Setup
        final Node expectedResult = new Node("Name", 0, 0, 0.0, 0.0, 0.0);

        // Run the test
        final Node result = busAPIControllerUnderTest.getBusStopInfo("22");

        // Verify the results
        assertEquals("22", result.getStopId());
        assertEquals(null, result.getName());
        assertEquals(53.37350306, result.getLatitude());
    }


    @Test
    void testGetRouteInformation() throws Exception {
        // Setup

        // Run the test
        final BusRouteList result = busAPIControllerUnderTest.getRouteInformation("routeNumber");

        // Verify the results
    }

}
