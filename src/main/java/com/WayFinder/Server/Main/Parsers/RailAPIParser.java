package com.WayFinder.Server.Main.Parsers;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import com.WayFinder.Server.Main.Models.Rail;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class RailAPIParser {

    public static ArrayList<Rail> Parse(String luasData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(luasData)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("objStation");
            ArrayList<Rail> rails = new ArrayList<Rail>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Rail rail = new Rail();

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    rail.StationName = eElement.getElementsByTagName("StationDesc").item(0).getTextContent();
                    rail.Latitude = Double.parseDouble(eElement.getElementsByTagName("StationLatitude").item(0).getTextContent());
                    rail.Longitude = Double.parseDouble(eElement.getElementsByTagName("StationLongitude").item(0).getTextContent());
                    rail.StationCode = eElement.getAttribute("StationCode");
                    rail.StationId = Integer.parseInt(eElement.getAttribute("StationId"));
                    rails.add(rail);
                }
            }
            return rails;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Rail>();
        }
    }
}