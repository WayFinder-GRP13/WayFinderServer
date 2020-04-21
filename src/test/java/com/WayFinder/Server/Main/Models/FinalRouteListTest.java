package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinalRouteListTest {

    private FinalRouteList finalRouteListUnderTest;

    @BeforeEach
    void setUp() {
        finalRouteListUnderTest = new FinalRouteList();
    }

    @Test
    void testFinalRouteListConstructor() {
        assertEquals(null, finalRouteListUnderTest.getFinalRouteList());

    }

}
