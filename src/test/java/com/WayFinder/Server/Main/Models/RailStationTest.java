package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RailStationTest {

    private RailStation railStationUnderTest;

    @BeforeEach
    void setUp() {
        railStationUnderTest = new RailStation();
    }

    @Test
    void testRailStationConstructor() {
        assertEquals(0.0, railStationUnderTest.Latitude);
    }
}
