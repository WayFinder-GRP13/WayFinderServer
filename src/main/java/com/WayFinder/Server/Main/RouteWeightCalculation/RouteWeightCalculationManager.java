package com.WayFinder.Server.Main.RouteWeightCalculation;

import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.RouteWeightCalculation.Bus.BusWeightCalculation;
import com.WayFinder.Server.Main.RouteWeightCalculation.Train.TrainWeightCalculation;
import com.WayFinder.Server.Main.RouteWeightCalculation.Walk.WalkWeightCalculation;

import java.util.ArrayList;

public class RouteWeightCalculationManager {
    private BusWeightCalculation busWeightCalculation = new BusWeightCalculation();
    private WalkWeightCalculation walkWeightCalculation = new WalkWeightCalculation();
    private TrainWeightCalculation trainWeightCalculation = new TrainWeightCalculation();


    public ArrayList<Edge> calculateRouteWeights(ArrayList<Node> NodeList, RestAPIRequestInformation request){
        ArrayList<Edge> edgeList = new ArrayList<>();
        //cycles through the list to size minus one
        //selects two nodes and calculates the weight between them for each transport type
        for(int i=0;i<NodeList.size()-1;i++){
            //ArrayList<Integer> weightList = new ArrayList<>();
            Node firstNode = NodeList.get(i);
            Node secondNode = NodeList.get(i+1);
            System.out.println("Transport type node1 is: "+firstNode.getTransportType());
            System.out.println("Transport type node2 is: "+secondNode.getTransportType());

            //need to check what type of transport calls need to be made
            //walk
            if(firstNode.getTransportType()==0){
                //walk to walk
                if(secondNode.getTransportType()==0){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //walk to bus
                if(secondNode.getTransportType()==1){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //car to cycle
                if(secondNode.getTransportType()==2){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                }

                }

            // bus
            if(firstNode.getTransportType()==1){
                //bus to walk
                if(secondNode.getTransportType()==0){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //bus to bus
                if(secondNode.getTransportType()==1){
                    // walk
                    // bus
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    int busWeight = busWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    System.out.println("Weight for walking is: "+walkWeight);
                    System.out.println("Weight for bus is: "+busWeight);
                    //if bus weight largest use bus weight for edge
                    if(busWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,busWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>busWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                    }//if weights tie then default is bus weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,busWeight,1));
                    }

                }
                //bus to cycle
                if(secondNode.getTransportType()==2){
                    // walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //bus to Train
                if(secondNode.getTransportType()==3){
                    // walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }

            }

            //train
            if(firstNode.getTransportType()==2){
                //train to walk
                if(secondNode.getTransportType()==0){
                    //walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //train to bus
                if(secondNode.getTransportType()==1){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));


                }
                //train to cycle
                if(secondNode.getTransportType()==2){
                    //call walk
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }

                // train to train
                if(secondNode.getTransportType()==3){
                    //walk
                    // train
                    int walkWeight = walkWeightCalculation.getNodeWeight(firstNode,secondNode,request);
                    int TrainWeight = trainWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if bus weight largest use bus weight for edge
                    if(TrainWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,TrainWeight,3));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>TrainWeight){
                            edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                        }//if weights tie then default is bus weight for edge
                        else{
                            edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,TrainWeight,3));
                        }


                }

            }
        }
        return edgeList;
    }
}
