package com.WayFinder.Server.Main.DijkstraAlgorithm;

import com.WayFinder.Server.Main.NodeCreation.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraAlgorithmManager {
    private List<Node> nodes;
    private List<Edge> edges;

    public void ExecuteAlgorithm(ArrayList<Node> NodeStopList) {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        //adds nodes to node list
        for (int i = 0; i < NodeStopList.size()-1; i++) {
            nodes.add(NodeStopList.get(i));
        }

        // for each node it adds a lane to the next node from the current node with a weight
        for(int j=0;j<nodes.size()-1;j++){
            addLane(nodes.get(j).getStopId()+":Lane", j, j+1, j*2+2);
        }


            //how lanes are added
            //addLane("Edge_0", 0, 1, 85);
    //        addLane("Edge_1", 0, 2, 217);
    //        addLane("Edge_2", 0, 4, 173);
    //        addLane("Edge_3", 2, 6, 186);
    //        addLane("Edge_4", 2, 7, 103);
    //        addLane("Edge_5", 3, 7, 183);
    //        addLane("Edge_6", 5, 8, 250);
    //        addLane("Edge_7", 8, 9, 84);
    //        addLane("Edge_8", 7, 9, 167);
    //        addLane("Edge_9", 4, 9, 502);
    //        addLane("Edge_10", 9, 10, 40);
    //        addLane("Edge_11", 1, 10, 600);
        // Lets get the best path
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Node> path = dijkstra.getPath(nodes.get(nodes.size()-1));


        for (Node node : path) {
            System.out.println(node.getStopId());
        }

    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int weight) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), weight );
        edges.add(lane);
    }
}
