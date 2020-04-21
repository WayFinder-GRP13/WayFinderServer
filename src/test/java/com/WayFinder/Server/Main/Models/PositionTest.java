package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    private Position positionUnderTest;

    @BeforeEach
    void setUp() {
        positionUnderTest = new Position();
    }

    @Test
    void testPositionConstructor() {
        assertEquals(0.0, positionUnderTest.Latitude);
    }
}
