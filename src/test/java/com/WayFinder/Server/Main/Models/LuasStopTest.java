package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuasStopTest {

    private LuasStop luasStopUnderTest;

    @BeforeEach
    void setUp() {
        luasStopUnderTest = new LuasStop("abv", 52.5555, -23.0, "pron");
    }

    @Test
    void testLuasStopConstructor() {
        assertEquals("abv", luasStopUnderTest.Abbreviation);
        assertEquals(52.5555, luasStopUnderTest.Latitude);
        assertEquals(-23.0, luasStopUnderTest.Longitude);
    }

}
