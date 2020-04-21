package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalkSettingsTest {

    private WalkSettings walkSettingsUnderTest;

    @BeforeEach
    void setUp() {
        walkSettingsUnderTest = new WalkSettings(false, 0);
    }

    @Test
    void testWalkConstructor() {
        assertEquals(false,walkSettingsUnderTest.isEnabled());
        assertEquals(0,walkSettingsUnderTest.getBugValue());

        // reset value
        walkSettingsUnderTest.setBugValue(25);

        //test that it was reset
        assertEquals(25,walkSettingsUnderTest.getBugValue());
    }

}
