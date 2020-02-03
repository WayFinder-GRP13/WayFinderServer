package com.WayFinder.Server.Main.Parsers;

import java.util.ArrayList;
import org.json.*;

import com.WayFinder.Server.Main.Models.Bike;
import com.WayFinder.Server.Main.Models.Position;

public class BikesAPIParser {

    public static ArrayList<Bike> Parse(String bikeData){
       ArrayList<Bike> bikes = new ArrayList<Bike>();
       JSONArray jsonarray = new JSONArray(bikeData);
       for (int i = 0; i < jsonarray.length(); i++){
           JSONObject jsonobject = jsonarray.getJSONObject(i);
           Bike bike =  new Bike();
           
           bike.Number = jsonobject.getInt("number");
           bike.Name  = jsonobject.getString("name");
           bike.Address = jsonobject.getString("address");
           Position position = new Position();
           JSONObject positionJson = jsonobject.getJSONObject("position");
           position.Latitude = positionJson.getDouble("lat");
           position.Longitude = positionJson.getDouble("lng");
           bike.Position = position;
           bike.BikeStands = jsonobject.getInt("bike_stands");
           bike.AvailableBikeStands = jsonobject.getInt("available_bike_stands");
           bike.AvailableBikes = jsonobject.getInt("available_bikes");
           bike.Status = jsonobject.getString("status");
           bikes.add(bike);     
       }     
       return bikes;
    }


}