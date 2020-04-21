package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransportSettingsTest {

    private TransportSettings transportSettingsUnderTest;

    @BeforeEach
    void setUp() {
        transportSettingsUnderTest = new TransportSettings(true, 1) {
        };
    }

    @Test
    void testTransportSettingsConstructor() {
        assertEquals(1,transportSettingsUnderTest.getBugValue());
        assertEquals(true,transportSettingsUnderTest.isEnabled());


    }
}
