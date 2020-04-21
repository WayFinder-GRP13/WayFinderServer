package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestAPIControllerTest {

    private RestAPIController restAPIControllerUnderTest;

    @BeforeEach
    void setUp() {
        restAPIControllerUnderTest = new RestAPIController();
    }

    @Test
    void testGetDistance() {
        // Setup

        // Run the test
        final double result = restAPIControllerUnderTest.getDistance(0.0, 0.0, 0.0, 0.0, "unit");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testIamalive() {
        // Setup

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.iamalive();

        // Verify the results
    }

    @Test
    void testIndex() {
        // Setup

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.index("name");

        // Verify the results
    }

    @Test
    void testGetUser() {
        // Setup

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.getUser(0);

        // Verify the results
    }

    @Test
    void testGetRoute() {
        // Setup
        final RestAPIRequestInformation request = null;

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.getRoute(request);

        // Verify the results
    }

    @Test
    void testGetLuasRoute() {
        // Setup
        final RestAPIRequestInformation request = null;

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.getLuasRoute(request);

        // Verify the results
    }

    @Test
    void testRailResponse() {
        // Setup
        final RestAPIRequestInformation request = null;

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.railResponse(request);

        // Verify the results
    }

    @Test
    void testUpdateUserList() {
        // Setup

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.updateUserList("name", 0L);

        // Verify the results
    }

    @Test
    void testRemoveUserList() {
        // Setup

        // Run the test
        final ResponseEntity result = restAPIControllerUnderTest.removeUserList(0L);

        // Verify the results
    }
}
