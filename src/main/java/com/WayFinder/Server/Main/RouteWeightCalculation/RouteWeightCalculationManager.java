package com.WayFinder.Server.Main.RouteWeightCalculation;

import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.RouteWeightCalculation.Bus.BusWeightCalculation;

import java.util.ArrayList;

public class RouteWeightCalculationManager {
    private BusWeightCalculation busWeightCalculation=new BusWeightCalculation();


    public ArrayList<Edge> calculateRouteWeights(ArrayList<Node> NodeList){
        ArrayList<Edge> edgeList = new ArrayList<>();
        //cycles through the list to size minus one
        //selects two nodes and calculates the weight between them for each transport type
        for(int i=0;i<NodeList.size()-1;i++){
            //ArrayList<Integer> weightList = new ArrayList<>();
            Node firstNode = NodeList.get(i);
            Node secondNode = NodeList.get(i+1);

            //need to check what type of transport calls need to be made
            //car
            if(firstNode.getTransportType()==0){
                //car to car
                if(secondNode.getTransportType()==0){
                    //call car
                    //call walk

                }
                //car to bus
                if(secondNode.getTransportType()==1){
                    //call car
                    //call walk

                }
                //car to cycle
                if(secondNode.getTransportType()==2){
                    //call car
                    //call walk
                }

                }

            // bus
            if(firstNode.getTransportType()==1){
                //bus to car
                if(secondNode.getTransportType()==0){
                    //call walk

                }
                //bus to bus
                if(secondNode.getTransportType()==1){
                    // walk
                    // bus
                    int walkWeight = 2;
                    int busWeight = busWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if bus weight largest use bus weight for edge
                    if(busWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,busWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>busWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,4));
                    }//if weights tie then default is bus weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,busWeight,1));
                    }

                }
                //bus to cycle
                if(secondNode.getTransportType()==2){
                    // walk

                }

            }

            //train
            if(firstNode.getTransportType()==2){
                //train to car
                if(secondNode.getTransportType()==0){
                    //walk

                }
                //train to bus
                if(secondNode.getTransportType()==1){
                    //walk
                    //taxi? car?

                }
                //train to cycle
                if(secondNode.getTransportType()==2){
                    //walk

                }

            }
        }
        return edgeList;
    }
}
