package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;

public class Edge {
    private final String id;
    private final Node source;
    private final Node destination;
    private final int weight;
    private final int transportType;

    public Edge(String id, Node source, Node destination, int weight, int transportType) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.transportType = transportType;
    }

    public String getId() {
        return id;
    }
    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

    public int getTransportType() {
        return transportType;
    }
}
