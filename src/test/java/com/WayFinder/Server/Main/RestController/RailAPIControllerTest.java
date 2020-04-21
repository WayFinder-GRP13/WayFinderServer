package com.WayFinder.Server.Main.RestController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RailAPIControllerTest {

    private RailAPIController railAPIControllerUnderTest;

    @BeforeEach
    void setUp() {
        railAPIControllerUnderTest = new RailAPIController();
    }

    @Test
    void testGetStations() throws Exception {
        // Setup

        // Run the test
        final ResponseEntity result = railAPIControllerUnderTest.getStations();
        assertEquals("200 OK",result.getStatusCode().toString());

        // Verify the results
    }




    @Test
    void testGetRailInfo() throws Exception {
        // Setup

        // Run the test
        final ResponseEntity result = railAPIControllerUnderTest.getRailInfo("222");


        assertEquals("200 OK",result.getStatusCode().toString());
        // Verify the results
    }


}
