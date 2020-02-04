package com.WayFinder.Server.Main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import com.WayFinder.Server.Main.Models.Rail;
import com.WayFinder.Server.Main.Parsers.RailAPIParser;

@RestController
public class RailAPIController {
    // "/bikes?lat=12121&long=2232"
    @GetMapping(value = "/rail")
    public ResponseEntity getRailInfo() throws IOException, SAXException, ParserConfigurationException
    {
        URL url = new URL("https://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");    
        con.setRequestProperty("Content-Type", "application/xml");

        int responseCode = con.getResponseCode();
        String readLine = null;
        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            while((readLine = in.readLine()) != null){
                response.append(readLine);
            }
            in.close();
            ArrayList<Rail> railInfo = new ArrayList<Rail>();
            railInfo = RailAPIParser.Parse(response.toString());
            return ResponseEntity.ok(railInfo);
        }
        else{
            return ResponseEntity.ok("System Error");
        }
    }
}