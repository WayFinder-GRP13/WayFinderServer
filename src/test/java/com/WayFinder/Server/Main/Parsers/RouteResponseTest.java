package com.WayFinder.Server.Main.Parsers;

import org.junit.jupiter.api.BeforeEach;

class RouteResponseTest {

    private RouteResponse routeResponseUnderTest;

    @BeforeEach
    void setUp() {
        routeResponseUnderTest = new RouteResponse("overviewPolyline", "route", "departureTime", 0);
    }
}
