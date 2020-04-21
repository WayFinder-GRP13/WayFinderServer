package com.WayFinder.Server.Main.UserSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class UserSettingsTest {

    @Mock
    private BusSettings mockBusSettings;
    @Mock
    private RailSettings mockRailSettings;
    @Mock
    private CarSettings mockCarSettings;
    @Mock
    private WalkSettings mockWalkSettings;
    @Mock
    private CycleSettings mockCycleSettings;
    @Mock
    private ScaleSettings mockScaleSettings;

    private UserSettings userSettingsUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userSettingsUnderTest = new UserSettings(111, mockBusSettings, mockRailSettings, mockCarSettings, mockWalkSettings, mockCycleSettings, mockScaleSettings);
    }

    @Test
    void testUserSettingConstructor() {
        assertEquals(mockBusSettings,userSettingsUnderTest.getBusSettings());
        assertEquals(mockCarSettings,userSettingsUnderTest.getCarSettings());
        assertEquals(mockCycleSettings,userSettingsUnderTest.getCycleSettings());
        assertEquals(111,userSettingsUnderTest.getId());
        assertEquals(mockScaleSettings,userSettingsUnderTest.getScaleSettings());


    }


}
