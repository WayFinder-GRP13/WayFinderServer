package com.WayFinder.Server.Main.Models;

import com.WayFinder.Server.Main.NodeCreation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class FinalRouteTest {

    @Mock
    private Node mockOrigin;
    @Mock
    private Node mockDestination;

    private FinalRoute finalRouteUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        finalRouteUnderTest = new FinalRoute(mockOrigin, mockDestination, "testing polyline string", 0, 0, "55", "departureTime");
    }
    @Test
    void testFinalRouteConstructor() {
        assertEquals(mockOrigin, finalRouteUnderTest.getOrigin());
        assertEquals(mockDestination, finalRouteUnderTest.getDestination());
        assertEquals("testing polyline string", finalRouteUnderTest.getOverviewPolyline());
        assertEquals("55", finalRouteUnderTest.getRouteNumber());

    }
}
