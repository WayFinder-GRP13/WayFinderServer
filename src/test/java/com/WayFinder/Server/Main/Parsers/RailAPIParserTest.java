package com.WayFinder.Server.Main.Parsers;

import com.WayFinder.Server.Main.Models.RailInfo;
import com.WayFinder.Server.Main.Models.RailStation;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class RailAPIParserTest {

    @Test
    void testParseStations() {
        // Setup

//        // Run the test
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String         line = null;
//        StringBuilder  stringBuilder = new StringBuilder();
//        String         ls = System.getProperty("line.separator");
//
//        try {
//            while((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//                stringBuilder.append(ls);
//            }
//
//             stringBuilder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Path path = Paths.get("getAllStationsXML.xml");
//        String content = "";
//        try {
//            content = Files.readString(path, StandardCharsets.US_ASCII);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        final ArrayList<RailStation> result = RailAPIParser.parseStations("railData");

        // Verify the results

    }
    @Test
    void testParseRailInfo() {
        // Setup

        // Run the test
        final ArrayList<RailInfo> result = RailAPIParser.parseRailInfo("railData");

        // Verify the results
    }
}
