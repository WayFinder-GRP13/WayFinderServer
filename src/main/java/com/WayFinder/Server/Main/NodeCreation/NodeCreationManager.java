package com.WayFinder.Server.Main.NodeCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NodeCreationManager {
    private ArrayList<Node> BusNodeList;

    public NodeCreationManager(){
        BusNodeList = new ArrayList();
    }

    public double distanceTo(double lat1,double lon1,double lat2,double lon2, String unit) {
//
//        return Math.acos(Math.sin(StartLat*(180/Math.PI)) * Math.sin(StartLat) +
//                Math.cos(StartLat*(180/Math.PI)) * Math.cos(EndLng*(180/Math.PI)) *
//                        Math.cos(StartLng*(180/Math.PI) - EndLat*(180/Math.PI))) * radius;
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
        double StartLatCOORD=StartLat;
        double EndLatCOORD=EndLat;
        double StartLngCOORD=StartLng;
        double EndLngCOORD=EndLng;
        System.out.println("Distance is: "+distanceTo(StartLat, StartLng,EndLat, EndLng, "K"));

        double distance = distanceTo(StartLat, StartLng,EndLat, EndLng, "K");

        double angle = getAngle(StartLat,StartLng,EndLat,EndLng);

        //top quadrant
        if (angle>=45.0&&angle<=135.0){
            StartLatCOORD = StartLat - ((distance / 2) / (110.574));
            EndLatCOORD = EndLat + ((distance / 2) / (110.574));
        }
        //left quadrant
        else if(angle>=135.0&&angle<=-135.0){
            StartLngCOORD = StartLng - (distance / 2) / (111.320*Math.cos(Math.toRadians(StartLatCOORD)));
            EndLngCOORD = EndLng + (distance / 2) / (111.320*Math.cos(Math.toRadians(EndLatCOORD)));
        }
        //bottom quadrant
        else if(angle>=-135.0&&angle<=-45.0){
            StartLatCOORD = StartLat - ((distance / 2) / (110.574));
            EndLatCOORD = EndLat + ((distance / 2) / (110.574));
        }
        // right quadrant
        else if(angle>=-45.0&&angle<=45.0){
            StartLngCOORD = StartLng - (distance / 2)/(111.320*Math.cos(Math.toRadians(StartLatCOORD)));
            EndLngCOORD = EndLng + (distance / 2) / (111.320 * Math.cos(Math.toRadians(EndLatCOORD)));
        }

        System.out.println("This is the new position for the boundding box: lat: "+EndLatCOORD+","+EndLngCOORD);
        System.out.println("This is the new position for the boundding box: lat: "+StartLatCOORD+","+StartLngCOORD);


        String BusNodesQuery =  "SELECT *\n" +
                                "FROM dublinbusstops\n" +
                                "WHERE \n" +
                                "  dublinbusstops.geom && ST_MakeEnvelope("+StartLatCOORD+","+ StartLngCOORD+","+ EndLatCOORD+","+ EndLngCOORD+",4326);";

        //database connection
        String url = "jdbc:postgresql://ec2-100-26-88-55.compute-1.amazonaws.com:5432/d7skpcjr074hru";

        //connection properties
        Properties props = new Properties();
        props.setProperty("user","qsmlljfzcqdiux");
        props.setProperty("password","171628e45eb433ca4a6963ee5dd08fe73f6521a06e96b8d6ab5ee2393f446905");
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


        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(NodeCreationManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return BusNodeList;
    }

}





