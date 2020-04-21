package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarSettingsTest {

    private CarSettings carSettingsUnderTest;

    @BeforeEach
    void setUp() {
        carSettingsUnderTest = new CarSettings(false, 0);
    }


    @Test
    void testCarConstructor() {
        assertEquals(false,carSettingsUnderTest.isEnabled());
        assertEquals(0,carSettingsUnderTest.getBugValue());

        // reset value
        carSettingsUnderTest.setBugValue(20);

        //test that it was reset
        assertEquals(20,carSettingsUnderTest.getBugValue());
    }
}
