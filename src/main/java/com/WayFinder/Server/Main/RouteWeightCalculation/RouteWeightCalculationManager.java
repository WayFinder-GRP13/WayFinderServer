package com.WayFinder.Server.Main.RouteWeightCalculation;
import com.WayFinder.Server.Main.RouteWeightCalculation.Walk.WalkWeightCalculation;
import com.WayFinder.Server.Main.DijkstraAlgorithm.Edge;
import com.WayFinder.Server.Main.NodeCreation.Node;
import com.WayFinder.Server.Main.RouteWeightCalculation.Bus.BusWeightCalculation;
import com.WayFinder.Server.Main.PerferenceCalculation.PreferenceCalculationManager;
import com.WayFinder.Server.Main.RouteWeightCalculation.Car.CarWeightCalculation;

import java.util.ArrayList;



public class RouteWeightCalculationManager {
    static double distance = 10;
    
    static double walkWeight;
    public static  PreferenceCalculationManager weightCalculate = new PreferenceCalculationManager();
    //public final double weighB = weightCalculate.getBus();

    private BusWeightCalculation busWeightCalculation=new BusWeightCalculation();
    private WalkWeightCalculation walkWeightCalculation=new WalkWeightCalculation();
    private CarWeightCalculation carWeightCalculation=new CarWeightCalculation();

   /* public static void main(String[] args){
        double busWeigh = weightCalculate.getBus();
        System.out.println(busWeigh);
    
    }*/


    public ArrayList<Edge> calculateRouteWeights(ArrayList<Node> NodeList){
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
            //car
            if(firstNode.getTransportType()==0){
                //car to car
                if(secondNode.getTransportType()==0){
                   
                    //call walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                   
                    // call car
                    double carWeight = carWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if car weight largest use car weight for edge
                    if(carWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>carWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                    }//if weights tie then default is car weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));
                    }

                }
                //car to bus
                if(secondNode.getTransportType()==1){
                    //call car
                    //call walk

                    //call walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                   
                    // call car
                    double carWeight = carWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if car weight largest use car weight for edge
                    if(carWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>carWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                    }//if weights tie then default is car weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));
                    }


                }
                //car to cycle
                if(secondNode.getTransportType()==2){
                    //call car
                    //call walk

                    //call walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                   
                    // call car
                    double carWeight = carWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if car weight largest use car weight for edge
                    if(carWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>carWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                    }//if weights tie then default is car weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));
                    }

                }

                }

            // bus
            if(firstNode.getTransportType()==1){
                //bus to car
                if(secondNode.getTransportType()==0){
                    //call walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                    

                }
                //bus to bus
                if(secondNode.getTransportType()==1){
                    
                    //double distance =2;
                    
                
                    // walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                    // bus
                    double busWeight = busWeightCalculation.getNodeWeight(firstNode,secondNode);
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
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }

            }

            //train
            if(firstNode.getTransportType()==2){
                //train to car
                if(secondNode.getTransportType()==0){
                    //walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }
                //train to bus
                if(secondNode.getTransportType()==1){
                    //walk
                    //taxi? car?

                    //call walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                   
                    // call car
                    double carWeight = carWeightCalculation.getNodeWeight(firstNode,secondNode);
                    //if car weight largest use car weight for edge
                    if(carWeight>walkWeight){

                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));

                    }else //if walk weight largest use walk weight for edge
                        if(walkWeight>carWeight){
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
                    }//if weights tie then default is car weight for edge
                        else{
                        edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));
                    }
                }
                //train to cycle
                if(secondNode.getTransportType()==2){
                    //walk
                    walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                    edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));

                }

            }
        }
        return edgeList;
    }

   /* turning car vs walk to a class --> to: do dring refactoring
   public <Edge> WalkVsCar(Node firstNode, Node secondNode)
    {
        new Edge edgeList;
        //call walk
        walkWeight= walkWeightCalculation.getNodeWeight(firstNode, secondNode);
                   
        // call car
        double carWeight = carWeightCalculation.getNodeWeight(firstNode,secondNode);
        //if car weight largest use car weight for edge
        if(carWeight>walkWeight){

            edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));

        }else //if walk weight largest use walk weight for edge
            if(walkWeight>carWeight){
                edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,walkWeight,0));
        }//if weights tie then default is car weight for edge
        else{
            edgeList.add(new Edge(Integer.toString(i),firstNode,secondNode,carWeight,1));
        }
        return edgeList;
    }*/
}
