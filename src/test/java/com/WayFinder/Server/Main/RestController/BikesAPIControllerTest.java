package com.WayFinder.Server.Main.RestController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BikesAPIControllerTest {

    private BikesAPIController bikesAPIControllerUnderTest;

    @BeforeEach
    void setUp() {
        bikesAPIControllerUnderTest = new BikesAPIController();
    }

    @Test
    void testGetBikes() throws Exception {
        // Setup

        // Run the test
        final ResponseEntity result = bikesAPIControllerUnderTest.getBikes();

        // Verify the results
    }


}
