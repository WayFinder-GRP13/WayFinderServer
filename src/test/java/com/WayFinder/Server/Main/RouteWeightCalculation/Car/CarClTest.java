package com.WayFinder.Server.Main.RouteWeightCalculation.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarClTest {

    private CarCl carClUnderTest;

    @BeforeEach
    void setUp() {
        carClUnderTest = new CarCl();
    }

    @Test
    void testCarWeight() {
        // Setup

        // Run the test
        final double result = carClUnderTest.CarWeight(20.0);

        // Verify the results
        assertEquals(33.677777, result, 0.0001);
    }

    @Test
    void testCO2CalCar() {
        // Setup

        // Run the test
        final double result = carClUnderTest.CO2CalCar(30.0);

        // Verify the results
        assertEquals(35.09999999999, result, 0.0001);
    }

    @Test
    void testSpeedOfCar() {
        // Setup

        // Run the test
        final double result = carClUnderTest.SpeedOfCar(20.0);

        // Verify the results
        assertEquals(0.2777777, result, 0.0001);
    }

    @Test
    void testCostOfCar() {
        // Setup

        // Run the test
        final double result = carClUnderTest.CostOfCar(30.0);

        // Verify the results
        assertEquals(48.0, result, 0.0001);
    }
}
