package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaleSettingsTest {

    private ScaleSettings scaleSettingsUnderTest;

    @BeforeEach
    void setUp() {
        scaleSettingsUnderTest = new ScaleSettings(6, 5, 8);
    }


    @Test
    void testScaleConstructor() {
        assertEquals(5, scaleSettingsUnderTest.getCostScale());
        assertEquals(6, scaleSettingsUnderTest.getEnvironmentalScale());
        assertEquals(8, scaleSettingsUnderTest.getSpeedScale());

        // reset value
        scaleSettingsUnderTest.setCostScale(20);

        //test that it was reset
        assertEquals(20, scaleSettingsUnderTest.getCostScale());
    }

}
