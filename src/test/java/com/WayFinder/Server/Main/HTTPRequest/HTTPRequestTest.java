package com.WayFinder.Server.Main.HTTPRequest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTTPRequestTest {

    private HTTPRequest httpRequestUnderTest;

    @BeforeEach
    void setUp() {
        httpRequestUnderTest = new HTTPRequest();
    }

    @Test
    void testSendHTTPRequest() {
        // Setup

        // Run the test
        final String result = httpRequestUnderTest.sendHTTPRequest("https://www.google.com/");

        // Verify the results
        Assert.assertThat(result, CoreMatchers.containsString("http://schema.org/WebPage"));
    }
}
