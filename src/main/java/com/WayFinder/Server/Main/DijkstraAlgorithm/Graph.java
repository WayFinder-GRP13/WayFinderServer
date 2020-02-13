package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.List;

public class Graph {
    private final List<Node> nodes;
    private final List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getVertexes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
