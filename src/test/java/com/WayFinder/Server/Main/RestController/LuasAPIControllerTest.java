package com.WayFinder.Server.Main.RestController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LuasAPIControllerTest {

    private LuasAPIController luasAPIControllerUnderTest;

    @BeforeEach
    void setUp() {
        luasAPIControllerUnderTest = new LuasAPIController();
    }

    @Test
    void testGetLuasStops() throws Exception {
        // Setup

        // Run the test
        final ResponseEntity result = luasAPIControllerUnderTest.getLuasStops();

        // Verify the results
    }


}
