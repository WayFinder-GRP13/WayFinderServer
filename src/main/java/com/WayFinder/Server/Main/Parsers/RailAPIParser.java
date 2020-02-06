package com.WayFinder.Server.Main.Parsers;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import com.WayFinder.Server.Main.Models.RailInfo;
import com.WayFinder.Server.Main.Models.RailStation;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class RailAPIParser {

    public static ArrayList<RailStation> parseStations(String railData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(railData)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("objStation");
            ArrayList<RailStation> rails = new ArrayList<RailStation>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                RailStation rail = new RailStation();

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    rail.StationName = eElement.getElementsByTagName("StationDesc").item(0).getTextContent();
                    rail.Latitude = Double.parseDouble(eElement.getElementsByTagName("StationLatitude").item(0).getTextContent());
                    rail.Longitude = Double.parseDouble(eElement.getElementsByTagName("StationLongitude").item(0).getTextContent());
                    rail.StationCode = eElement.getElementsByTagName("StationCode").item(0).getTextContent();
                    rail.StationId = Integer.parseInt(eElement.getElementsByTagName("StationId").item(0).getTextContent());
                    rails.add(rail);
                }
            }
            return rails;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<RailStation>();
        }
    }

    public static ArrayList<RailInfo> parseRailInfo(String railData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(railData)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("objStationData");
            ArrayList<RailInfo> rails = new ArrayList<RailInfo>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                RailInfo railInfo = new RailInfo();

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    railInfo.StationCode = eElement.getElementsByTagName("Stationcode").item(0).getTextContent();
                    railInfo.Origin = eElement.getElementsByTagName("Origin").item(0).getTextContent();
                    railInfo.Destination = eElement.getElementsByTagName("Destination").item(0).getTextContent();
                    railInfo.DestinationTime = eElement.getElementsByTagName("Destinationtime").item(0).getTextContent();
                    railInfo.LastLocation = eElement.getElementsByTagName("Lastlocation").item(0).getTextContent();
                    railInfo.DueIn = Integer.parseInt(eElement.getElementsByTagName("Duein").item(0).getTextContent());
                    railInfo.ExpArrival = eElement.getElementsByTagName("Exparrival").item(0).getTextContent();
                    railInfo.ExpDepart = eElement.getElementsByTagName("Expdepart").item(0).getTextContent();
                    railInfo.Direction = eElement.getElementsByTagName("Direction").item(0).getTextContent();
                    railInfo.TrainType = eElement.getElementsByTagName("Traintype").item(0).getTextContent();
                    railInfo.LocationType = eElement.getElementsByTagName("Locationtype").item(0).getTextContent();
                    rails.add(railInfo);
                }
            }
            return rails;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<RailInfo>();
        }
    }
}