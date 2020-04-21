package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RailInfoTest {

    private RailInfo railInfoUnderTest;

    @BeforeEach
    void setUp() {
        railInfoUnderTest = new RailInfo();
    }

    @Test
    void testRailInfoConstructor() {
        assertEquals(0, railInfoUnderTest.DueIn);
    }
}
