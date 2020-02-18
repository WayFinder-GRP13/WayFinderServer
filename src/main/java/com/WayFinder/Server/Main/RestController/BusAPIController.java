
package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.Parsers.BusAPIParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;


@RestController
public class BusAPIController {

        //"/busstopinformation?stopid=147"
        @GetMapping(value = "/busstopinformation")
        public ArrayList<BusStop> getBusStopInfo(String stopNumber) throws IOException
        {
            URL url = new URL("https://data.smartdublin.ie/cgi-bin/rtpi/busstopinformation?stopid="+stopNumber);
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
                ArrayList<BusStop> busStopList;
                busStopList = BusAPIParser.ParseBusStop(response.toString());
                in.close();
                System.out.println("Success: "+busStopList.get(0).getBusStopID());
                ArrayList<String> list = busStopList.get(0).getBusRouteList();
                for(int i=0;i<list.size();i++){
                    System.out.println("stop: "+list.get(i));
                }
                return busStopList;
            }
            else{
                return null;
            }
        }


    //"/busRouteInformation?routeid=4"
    @GetMapping(value = "/busRouteInformation")
    public BusRoute getRouteInformation(String routeNumber) throws IOException
    {
        URL url = new URL("https://data.smartdublin.ie/cgi-bin/rtpi/routeinformation?routeid="+routeNumber+"&operator=bac");
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
            BusRoute busRoute;
            busRoute = BusAPIParser.ParseBusRoute(response.toString());
            in.close();
            ArrayList<BusStop> list = busRoute.getBusStopList();
            for(int i=0;i<list.size();i++){
            }
            return busRoute;
        }
        else{
            return null;
        }
    }
}

