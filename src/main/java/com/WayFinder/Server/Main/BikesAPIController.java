package com.WayFinder.Server.Main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

import com.WayFinder.Server.Main.Models.Bike;
import com.WayFinder.Server.Main.Parsers.BikesAPIParser;

@RestController
public class BikesAPIController {
    //"/bikes?lat=12121&long=2232"
    @GetMapping(value = "/bikes")
    public ResponseEntity getBikes() throws IOException
    {
        URL url = new URL("https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=215780862e2545500c919b4f5e8e2419ddc36b6c");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");    
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();
        String readLine = null;
        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            while((readLine = in.readLine()) != null){
                response.append(readLine);
            }
            ArrayList<Bike> bikes = new ArrayList<Bike>(); 
            bikes = BikesAPIParser.Parse(response.toString());
            in.close();
            return ResponseEntity.ok(bikes);
        }
        else{
            return ResponseEntity.ok("System Error");
        }
    }
}