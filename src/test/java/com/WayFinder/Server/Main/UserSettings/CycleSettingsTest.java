package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CycleSettingsTest {

    private CycleSettings cycleSettingsUnderTest;

    @BeforeEach
    void setUp() {
        cycleSettingsUnderTest = new CycleSettings(false, 0);
    }


    @Test
    void testCycleConstructor() {
        assertEquals(false, cycleSettingsUnderTest.isEnabled());
        assertEquals(0, cycleSettingsUnderTest.getBugValue());

        // reset value
        cycleSettingsUnderTest.setBugValue(30);

        //test that it was reset
        assertEquals(30, cycleSettingsUnderTest.getBugValue());
    }

}