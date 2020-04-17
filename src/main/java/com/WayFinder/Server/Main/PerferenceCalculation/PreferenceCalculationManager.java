package com.WayFinder.Server.Main.PerferenceCalculation;

import com.WayFinder.Server.Main.RouteWeightCalculation.Bus.BusCal;
import java.lang.Double;
//import com.WayFinder.Server.Main.RouteWeightCalculation.Bus;
import com.WayFinder.Server.Main.RouteWeightCalculation.Car.CarCl;

import com.WayFinder.Server.Main.RouteWeightCalculation.Walk.WalkWeightCalculation;
import com.WayFinder.Server.Main.RouteWeightCalculation.Train.TrainCal;
import com.WayFinder.Server.Main.RouteWeightCalculation.Cycle.CycleCal;
import com.WayFinder.Server.Main.UserSettings.ScaleSettings;

//rest api --> ojevt --> user 

public class PreferenceCalculationManager {
    public static double busRouteValue;
    public static double trainRouteValue;
    public static double walkRouteValue;
    public static double driveRouteValue;
    public static double bikeRouteValue;
    
    public double user = (double) 35.7;
    private ScaleSettings userScale = new ScaleSettings();
    double userCost = (double)userScale.getCostScale();
    double userSpeed = (double)userScale.getSpeedScale();
    double userEnviro = (double)userScale.getEnvironmentalScale();

    public static void main(String[] args) {
        double distance = (double) 20;

        Double best = routeSelector(distance, busRouteValue, trainRouteValue, walkRouteValue, driveRouteValue,
                bikeRouteValue);
        System.out.println(best);
    
    }

    boolean speed=false;
    boolean environemnt = false;
    boolean busR =false;
    
   //public double distance;

  // public class routeSelectionValue {// just added this class so that it won't show an error in the functions below

    public static double speedScale;
       
    public static  double environmentScale;

    public static  double costScale;
    /*  
        Takes distance, 
        choice of : bus =1, train =2, Walk = 3, Drice = 4, Bike =5
        speed vs evironemnet using boleans to activate the required one --> if both false then it checks for cost
    */

    public static double getSpeedScale(double distance, int choiceVar, boolean speed, boolean environment) {

        if (choiceVar == 1) {
            // bus
            if (speed = true) {
                final BusCal bus = new BusCal();
                final double totalTime = bus.SpeedBus(distance);
                speedScale = totalTime;
                return speedScale;
            } else if (environment = true) {
                final BusCal bus = new BusCal();
                final double totalCO2 = bus.CO2CalBus(distance);
                environmentScale = totalCO2;
                return (environmentScale);
            } else {// if neither is true --> gives cost
                final BusCal bus = new BusCal();
                final double totalCost = bus.costBus();
                costScale = totalCost;
                return costScale;

            }

            
        }
        if (choiceVar == 2) {
            if (speed = true) {
                TrainCal train = new TrainCal();
                double totalTime = train.SpeedOfTrain(distance);
                speedScale = totalTime;
                return speedScale;
            } else if (environment = true) 
            {
                TrainCal trian = new TrainCal();
                double totalCO2 = trian.CO2CalTrain(distance);
                environmentScale = totalCO2;
                return environmentScale;
            } else {// if neither is true --> gives cost
                TrainCal train = new TrainCal();
                double totalCost = train.CostTrain();
                costScale = totalCost;
                return costScale;
            }
            // train
        }
        if (choiceVar == 3) {
            // walk
             if (speed = true) {
                final WalkWeightCalculation walk = new WalkWeightCalculation();
                double totalTime = walk.Walkspeed(distance);
                speedScale = totalTime;
                return speedScale;
            } else if (environment = true) {
                environmentScale = (double) 0;
                // return 0
            }
        }

        if (choiceVar == 4) {
            // drive
            if (speed = true) {
                CarCl car = new CarCl();
                double totalTime = car.SpeedOfCar(distance);
                speedScale = totalTime;
                return (speedScale);
            } else if (environment = true) {
                CarCl car = new CarCl();
                double totalCO2 = car.CO2CalCar(distance);
                environmentScale = totalCO2;
                return environmentScale;
            }
            // if neither is true --> gives cost
            else {
                CarCl car = new CarCl();
                double totalCost = car.CostOfCar(distance);
                costScale = totalCost;

                return (costScale);
            }

        }
        if (choiceVar == 5) {
            // bike
            if (speed = true) {
                CycleCal cycle = new CycleCal();
                double totalTime = cycle.CycleTime(distance);
                speedScale = totalTime;
                return (speedScale);
            } else if (environment = true) {
                CycleCal cycle = new CycleCal();
                double totalCO2 = cycle.CO2CalCycle(distance);
                environmentScale = totalCO2;
                return environmentScale;
            } else {
                CycleCal cycle = new CycleCal();
                double totalCost = cycle.CycleCost(distance);
                costScale = totalCost;
                return costScale;
            }

        }
        return 0.0;

        // return speedScale;
    }

    
    /*public void setSpeedScale(final double speedScale) {
        this.speedScale = speedScale;
    }*/

    /*
        using the distance + user preference it calls getSpeed scale
        returns it

        Note: it is called by the RouteWeightClaculatonMager when needed to find weight between node
        Flow: RoutWeightCalculationManager -> 2 nodes --> WeightCalcuationX -> distance -->  getX -> distance + user prefernec --> getSpeedScale
    */
    public double getBus(double distance) {
        busRouteValue=(userSpeed/10)* getSpeedScale( distance ,1, true, false)+(userEnviro/10)*getSpeedScale( distance,1, false, true)+(userCost/10)*getSpeedScale( distance,1, false, false);
        return busRouteValue;
    }

    public double getDrive(double distance) {
        driveRouteValue=(userSpeed/10)*getSpeedScale( distance,4, true, false)+(userEnviro/10)*getSpeedScale( distance,4, false, true)+(userCost/10)*getSpeedScale( distance,4, false, false);
        return driveRouteValue;
    }

    public double getTrain(double distance) {
        trainRouteValue=(userSpeed/10)*getSpeedScale( distance,2, true, false)+(userEnviro/10)*getSpeedScale( distance,1, false, true)+(userCost/10)*getSpeedScale( distance,1, false, false);
        return trainRouteValue;
    }

    public double getBike(double distance) {
        bikeRouteValue=(userSpeed/10)*getSpeedScale( distance,5, true, false)+(userEnviro/10)*getSpeedScale( distance,5, false, true)+(userCost/10)*getSpeedScale( distance,5, false, false);
        return bikeRouteValue;
    }

    public double getWalk(double distance) {
        walkRouteValue=(userSpeed/10)*getSpeedScale( distance,3, true, false)+(userEnviro/10)*getSpeedScale( distance,1, false, true)+(userCost/10)*getSpeedScale( distance,1, false, false);
        return walkRouteValue;
    }

   /* public double getEnvironmentScale(final double distance) {

        return environmentScale;
    }

    public void setEnvironmentScale(final double environmentScale) {
        this.environmentScale = environmentScale;
    }

    public double getCostScale() {
        return costScale;
    }

    public void setCostScale(final double costScale) {
        this.costScale = costScale;
    }
    */

   /*public void routeSelectionValue(final double speed, final double environment, final double cost) {

        speedScale = speed;
        environmentScale = environment;
        costScale = cost;
    }*/
    //}

    public static double routeSelector(double distance,double busRouteValue,double trainRouteValue,double walkRouteValue,double driveRouteValue,
    double bikeRouteValue)
    {    
        
                           
        //busRouteValue=(user/10)* getSpeedScale( distance ,1, true, false)+(user/10)*getSpeedScale( distance,1, false, true)+(user/10)*getSpeedScale( distance,1, false, false);
        System.out.println("bus is ;" + busRouteValue);
       // trainRouteValue=(user/10)*getSpeedScale( distance,2, true, false)+(user/10)*getSpeedScale( distance,1, false, true)+(user/10)*getSpeedScale( distance,1, false, false);
        System.out.println("train is ;" +trainRouteValue);
        //walkRouteValue=(user.speedScale/10)*walk.speedScale( distance,3, true, false)+(user.environmentScale/10)*walk.environmentScale( distance,1, false, true)+(user.costScale/10)*walk.costScale( distance,1, false, false);
        //driveRouteValue=(user/10)*getSpeedScale( distance,4, true, false)+(user/10)*getSpeedScale( distance,4, false, true)+(user/10)*getSpeedScale( distance,4, false, false);
        System.out.println("drive is ;" + driveRouteValue);
       // bikeRouteValue=(user/10)*getSpeedScale( distance,5, true, false)+(user/10)*getSpeedScale( distance,5, false, true)+(user/10)*getSpeedScale( distance,5, false, false);
        System.out.println("cycle is ;" + bikeRouteValue);

        double bestRoute= Math.max(busRouteValue,Math.max(trainRouteValue, Math.max(driveRouteValue,Math.max(bikeRouteValue,walkRouteValue))));
       
        
       
       
        
        if (bestRoute == busRouteValue)
        System.out.println( "Bus is best route");
        if (bestRoute == trainRouteValue)
        System.out.println("Train is the best route");
       if (bestRoute == walkRouteValue)
       System.out.println("Walking is the best route");
        if (bestRoute == driveRouteValue)
        System.out.println("Driving is the best route");
        if (bestRoute == bikeRouteValue)
        System.out.println("Cycling is the best route");
        
        else{
            //routeSelectionTest.function1(); // Test has not yet uploaded
            System.out.println("nothing");
        }
        
        return bestRoute;

    }
}
