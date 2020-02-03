package com.WayFinder.Server.Main.Parsers;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import com.WayFinder.Server.Main.Models.Luas;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class LuasAPIParser {

    public static ArrayList<Luas> Parse(String luasData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(luasData)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("stop");
            ArrayList<Luas> luasStops = new ArrayList<Luas>();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Luas luas = new Luas();

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    luas.Abbreviation = eElement.getAttribute("abrev");
                    luas.Latitude = Double.parseDouble(eElement.getAttribute("lat"));
                    luas.Longitude = Double.parseDouble(eElement.getAttribute("long"));
                    luas.Pronunciation = eElement.getAttribute("pronunciation");
                    luasStops.add(luas);
                }
            }
            return luasStops;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Luas>();
        }
    }
}