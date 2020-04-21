package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RailSettingsTest {

    private RailSettings railSettingsUnderTest;

    @BeforeEach
    void setUp() {
        railSettingsUnderTest = new RailSettings(false, 0);
    }


    @Test
    void testRailConstructor() {
        assertEquals(false, railSettingsUnderTest.isEnabled());
        assertEquals(0, railSettingsUnderTest.getBugValue());

        // reset value
        railSettingsUnderTest.setBugValue(27);

        //test that it was reset
        assertEquals(27, railSettingsUnderTest.getBugValue());
    }

}


