package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuasLineTest {

    private LuasLine luasLineUnderTest;

    @BeforeEach
    void setUp() {
        luasLineUnderTest = new LuasLine();
    }


    @Test
    void testLuasLineConstructor() {
        assertEquals(null, luasLineUnderTest.Stops);
    }


}
