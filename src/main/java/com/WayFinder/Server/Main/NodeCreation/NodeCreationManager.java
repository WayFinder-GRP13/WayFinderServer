package com.WayFinder.Server.Main.NodeCreation;

import com.WayFinder.Server.Main.Models.BusStop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NodeCreationManager {
    private ArrayList<Node> BusNodeList;

    //pg:killall --app group13aseserver= to clear all connections
    public NodeCreationManager(){
        BusNodeList = new ArrayList();
    }

    public double distanceTo(double lat1,double lon1,double lat2,double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        }
        return dist;
    }

    public double getAngle(double StartLat,double StartLng,double EndLat,double EndLng){
        double dy = EndLat - StartLat;
        double dx = Math.cos(Math.PI/180*StartLat)*(EndLng - StartLng);
        double angle = Math.atan2(dy, dx);
        angle=angle*180/Math.PI;
        System.out.println("The angle is: "+angle);
        return angle;

    }
    public ArrayList<Node>  getNodes(double StartLat,double StartLng,double EndLat,double EndLng){//53.35, -6, 53.36, -6.4
        double ThresholdDistance=0.3; // this is in Km
        double StartLatCOORD=StartLat;
        double EndLatCOORD=EndLat;
        double StartLngCOORD=StartLng;
        double EndLngCOORD=EndLng;
        double distanceScale = 5.0;
        System.out.println("Distance is: "+distanceTo(StartLat, StartLng,EndLat, EndLng, "K"));

        double distance = distanceTo(StartLat, StartLng,EndLat, EndLng, "K");

        double angle = getAngle(StartLat,StartLng,EndLat,EndLng);

        // careful if altering this code: in western hemisphere minus latitude is right and plus latitude is left
        // right upper quadrant//distance / distanceScale
        if (0.0<=angle&&angle<=90.0){
            StartLatCOORD = StartLat - ((ThresholdDistance) / (110.574));//.----->
            EndLatCOORD = EndLat + ((ThresholdDistance) / (110.574));//<------. # note plus becuae minus latt is increasing direction
            StartLngCOORD = StartLng - (ThresholdDistance ) / (111.320*Math.cos(Math.toRadians(StartLatCOORD)));//down
            EndLngCOORD = EndLng + (ThresholdDistance ) / (111.320*Math.cos(Math.toRadians(EndLatCOORD)));//^
        }
        // left upper quadrant
        else if(90.0<=angle&&angle<=180.0){
            StartLatCOORD = StartLat - ((ThresholdDistance ) / (110.574));
            EndLatCOORD = EndLat + ((ThresholdDistance ) / (110.574));
            StartLngCOORD = StartLng + (ThresholdDistance) / (111.320*Math.cos(Math.toRadians(StartLatCOORD)));
            EndLngCOORD = EndLng - (ThresholdDistance) / (111.320*Math.cos(Math.toRadians(EndLatCOORD)));
        }
        //left bottom quadrant
        else if(-180.0<=angle&&angle<=-90.0){
            StartLatCOORD = StartLat + ((ThresholdDistance) / (110.574));
            EndLatCOORD = EndLat - ((ThresholdDistance) / (110.574));
            StartLngCOORD = StartLng + (ThresholdDistance ) / (111.320*Math.cos(Math.toRadians(StartLatCOORD)));
            EndLngCOORD = EndLng - (ThresholdDistance) / (111.320*Math.cos(Math.toRadians(EndLatCOORD)));
        }
        // right bottom quadrant
        else if(-90.0<=angle&&angle<=0.0){
            StartLatCOORD = StartLat + ((ThresholdDistance ) / (110.574));
            EndLatCOORD = EndLat - ((ThresholdDistance) / (110.574));
            StartLngCOORD = StartLng - (ThresholdDistance)/(111.320*Math.cos(Math.toRadians(StartLatCOORD)));
            EndLngCOORD = EndLng + (ThresholdDistance) / (111.320 * Math.cos(Math.toRadians(EndLatCOORD)));
        }
        System.out.println("Angle is: "+angle);
        System.out.println("This is the old position for the bounding box: lat: "+StartLat+","+StartLng);
        System.out.println("This is the old position for the bounding box: lat: "+EndLat+","+EndLng);
        System.out.println("This is the new position for the bounding box: lat: "+EndLatCOORD+","+EndLngCOORD);
        System.out.println("This is the new position for the bounding box: lat: "+StartLatCOORD+","+StartLngCOORD);


        String BusNodesQuery =  "SELECT *\n" +
                                "FROM dublinbusstops\n" +
                                "WHERE \n" +
                                "  dublinbusstops.geom && ST_MakeEnvelope("+StartLatCOORD+","+ StartLngCOORD+","+ EndLatCOORD+","+ EndLngCOORD+",4326);";


        String BusNodesDistanceQueryStartPoint =  "SELECT  st_distance_sphere(st_point("+StartLat+", "+StartLng+"), st_point(dublinbusstops.x, dublinbusstops.y))\n"+
                "FROM dublinbusstops\n"+
                "WHERE dublinbusstops.geom && ST_MakeEnvelope("+StartLatCOORD+","+ StartLngCOORD+","+ EndLatCOORD+","+ EndLngCOORD+", 4326)\n";



        String BusNodesDistanceQueryEndPoint =  "SELECT  st_distance_sphere(st_point("+EndLat+", "+EndLng+"), st_point(dublinbusstops.x, dublinbusstops.y))\n"+
                "FROM dublinbusstops\n"+
                "WHERE dublinbusstops.geom && ST_MakeEnvelope("+StartLatCOORD+","+ StartLngCOORD+","+ EndLatCOORD+","+ EndLngCOORD+", 4326)\n";


        //database connection
        String url = "jdbc:postgresql://ec2-46-137-177-160.eu-west-1.compute.amazonaws.com:5432/d9d9eb795ebrsl";
        //ec2-46-137-177-160.eu-west-1.compute.amazonaws.com

        //connection properties
        Properties props = new Properties();
        props.setProperty("user","bwtgzdutmqrmca");
        props.setProperty("password","8b0a6af0e5d2a05bd6a462e8fb42320d0786a857ee56fd4aa4df9193583bd697");
        props.setProperty("sslmode","require");


        //can be taken out later queries the database to ensure connection
        try (Connection con = DriverManager.getConnection(url, props);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }


        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(NodeCreationManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        long startTime = System.currentTimeMillis();
        try (Connection con = DriverManager.getConnection(url, props);
             Statement st1 = con.createStatement();
             ResultSet rs1 = st1.executeQuery(BusNodesQuery)) {

            ResultSetMetaData rsmd = rs1.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs1.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs1.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                String stopID = rs1.getString(2);
                int stopIDNumber = Integer.parseInt(stopID.substring(stopID.length()-4));
                Node busNode = new Node(rs1.getString(3),stopIDNumber,1,rs1.getDouble(4),rs1.getDouble(5),99);
                BusNodeList.add(busNode);
                System.out.println("");
            }
            long endTime = System.currentTimeMillis();

            System.out.println("That took " + (endTime - startTime) + " milliseconds");



        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(NodeCreationManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }



        //get distance to start point
        try (Connection con2 = DriverManager.getConnection(url, props);
             Statement st2 = con2.createStatement();
             ResultSet rs2 = st2.executeQuery(BusNodesDistanceQueryStartPoint)) {

            ResultSetMetaData rsmd = rs2.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int counter=0;
            while (rs2.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs2.getString(i);
                }
                Node currentStop = BusNodeList.get(counter);
                currentStop.setDistanceToStartLocation(rs2.getDouble(1));
                counter=counter+1;
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(NodeCreationManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }




        //get distance to end point
        try (Connection con3 = DriverManager.getConnection(url, props);
             Statement st3 = con3.createStatement();
             ResultSet rs3 = st3.executeQuery(BusNodesDistanceQueryEndPoint)) {

            ResultSetMetaData rsmd = rs3.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int counter=0;
            while (rs3.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs3.getString(i);
                }
                Node currentStop = BusNodeList.get(counter);
                currentStop.setDistanceToEndLocation(rs3.getDouble(1));
                System.out.println("");
                counter=counter+1;
            }


        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(NodeCreationManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        for(Node node:BusNodeList) {
            System.out.println("Bus Stop Number: "+node.getStopId());
            System.out.println("Distnace to start loaction: "+node.getDistanceToStartLocation());
            System.out.println("Distance to end location: "+node.getDistanceToEndLocation());
        }
        return BusNodeList;
    }

}


