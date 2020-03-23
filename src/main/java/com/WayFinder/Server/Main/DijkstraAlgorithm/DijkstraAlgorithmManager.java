package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraAlgorithmManager {
    private List<Node> nodes;
    private List<Edge> edges;

    public LinkedList<Node> ExecuteAlgorithm(ArrayList<Node> nodeStopList, ArrayList<Edge> edgeList) {
        nodes = nodeStopList;
        edges = edgeList;


        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Node> path = dijkstra.getPath(nodes.get(nodes.size()-1));


        for (Node node : path) {
            System.out.println(node.getStopId());
        }
        System.out.println("Dijestras algo final list size:"+path.size());
        return path;

    }
}
