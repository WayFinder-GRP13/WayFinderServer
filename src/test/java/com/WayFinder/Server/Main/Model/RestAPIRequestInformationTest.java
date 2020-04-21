package com.WayFinder.Server.Main.Model;

import com.WayFinder.Server.Main.UserSettings.UserSettings;
import com.google.maps.model.LatLng;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class RestAPIRequestInformationTest {

    @Mock
    private UserSettings mockSettings;

    private RestAPIRequestInformation restAPIRequestInformationUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        restAPIRequestInformationUnderTest = new RestAPIRequestInformation(777, "name", "63.2", "55.5", "62.4", "55.6", mockSettings);
    }

    @Test
    void testRestAPIRequestInformationConstructor() {
        System.out.println(restAPIRequestInformationUnderTest.getEndLocation());
        assertEquals(new LatLng(62.400000,55.600000).lat,restAPIRequestInformationUnderTest.getEndLocation().lat);
        assertEquals(new LatLng(62.400000,55.600000).lng,restAPIRequestInformationUnderTest.getEndLocation().lng);
        assertEquals(new LatLng(63.200000,55.500000).lat,restAPIRequestInformationUnderTest.getStartLocation().lat);
        assertEquals(new LatLng(62.400000,55.500000).lng,restAPIRequestInformationUnderTest.getStartLocation().lng);
        assertEquals(777,restAPIRequestInformationUnderTest.getId());
    }
}
