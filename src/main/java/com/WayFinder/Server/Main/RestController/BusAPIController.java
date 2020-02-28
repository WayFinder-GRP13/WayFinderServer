
package com.WayFinder.Server.Main.RestController;

import com.WayFinder.Server.Main.Models.BusRoute;
import com.WayFinder.Server.Main.Models.BusStop;
import com.WayFinder.Server.Main.NodeCreation.Node;
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
        public Node getBusStopInfo(String stopNumber) throws IOException
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
                Node busStop;
                busStop = BusAPIParser.ParseBusStop(response.toString());
                in.close();
                System.out.println("Success: "+busStop.getStopId());
                return busStop;
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
            ArrayList<Node> list = busRoute.getBusStopList();
            for(int i=0;i<list.size();i++){
            }
            return busRoute;
        }
        else{
            return null;
        }
    }
}

