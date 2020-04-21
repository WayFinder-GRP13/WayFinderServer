package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusSettingsTest {

    private BusSettings busSettingsUnderTest;

    @BeforeEach
    void setUp() {
        busSettingsUnderTest = new BusSettings(false, 0);
    }
    @Test
    void testBusConstructor() {
        assertEquals(false,busSettingsUnderTest.isEnabled());
        assertEquals(0,busSettingsUnderTest.getBugValue());

        // reset value
        busSettingsUnderTest.setBugValue(25);

        //test that it was reset
        assertEquals(25,busSettingsUnderTest.getBugValue());
    }
}
