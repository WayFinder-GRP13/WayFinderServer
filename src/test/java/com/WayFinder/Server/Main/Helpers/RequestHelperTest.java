package com.WayFinder.Server.Main.Helpers;

import com.WayFinder.Server.Main.Enums.RequestType;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestHelperTest {

    @Test
    void testIsValidRequest() {
        // Setup
        final RestAPIRequestInformation request = null;

        // Run the test
        final boolean result = RequestHelper.isValidRequest(request, RequestType.ROUTE);

        // Verify the results
        assertFalse(result);
    }
}
