package com.WayFinder.Server.Main.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuasInfoTest {

    private LuasInfo luasInfoUnderTest;

    @BeforeEach
    void setUp() {
        luasInfoUnderTest = new LuasInfo();
    }

    @Test
    void testLuasConstructor() {
        assertEquals(null, luasInfoUnderTest.LuasLine);
    }


}
