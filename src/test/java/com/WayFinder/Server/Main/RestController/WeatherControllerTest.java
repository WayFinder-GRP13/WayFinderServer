package com.WayFinder.Server.Main.RestController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherControllerTest {

    private WeatherController weatherControllerUnderTest;

    @BeforeEach
    void setUp() {
        weatherControllerUnderTest = new WeatherController();
    }

    @Test
    void testGetWeatherByCity() {
        // Setup

        // Run the test
        final Object result = weatherControllerUnderTest.getWeatherByCity("city", "country");

        // Verify the results
    }

    @Test
    void testGetForecastByCity() {
        // Setup

        // Run the test
        final Object result = weatherControllerUnderTest.getForecastByCity("city", "code");

        // Verify the results
    }
}
