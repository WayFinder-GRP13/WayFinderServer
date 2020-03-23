package com.WayFinder.Server.Main.Parsers;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import com.WayFinder.Server.Main.Models.LuasLine;
import com.WayFinder.Server.Main.Models.LuasStop;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class LuasAPIParser {

    public static ArrayList<LuasLine> Parse(String luasData) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(luasData)));
            doc.getDocumentElement().normalize();

            NodeList lineList = doc.getElementsByTagName("line");
            ArrayList<LuasLine> luasLines = new ArrayList<LuasLine>();
            for (int i = 0; i < lineList.getLength(); i++) {
                Node line = lineList.item(i);
                if (line.getNodeType() == Node.ELEMENT_NODE) {
                    Element linstopElement = (Element) line;
                    LuasLine luasLine = new LuasLine();
                    luasLine.LineName = linstopElement.getAttribute("name");

                    NodeList nList = line.getChildNodes();
                    //doc.getElementsByTagName("stop");
                    //nlist = nList.
                    ArrayList<LuasStop> luasStops = new ArrayList<LuasStop>();
                    for (int j = 0; j < nList.getLength(); j++) {
                        LuasStop luas = new LuasStop();

                        Node nNode = nList.item(j);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element stopElement = (Element) nNode;
                            luas.Abbreviation = stopElement.getAttribute("abrev");
                            luas.Latitude = Double.parseDouble(stopElement.getAttribute("lat"));
                            luas.Longitude = Double.parseDouble(stopElement.getAttribute("long"));
                            luas.Pronunciation = stopElement.getAttribute("pronunciation");
                            luasStops.add(luas);
                        }
                    }
                    luasLine.Stops = luasStops;
                    luasLines.add(luasLine);
                }
            }

            return luasLines;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<LuasLine>();
        }
    }
}