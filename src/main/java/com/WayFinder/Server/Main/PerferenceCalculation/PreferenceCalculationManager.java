package com.WayFinder.Server.Main.PerferenceCalculation;


public class PreferenceCalculationManager {

    public class routeSelectionValue {// just added this class so that it won't show an error in the functions below

        public double speedScale;
        public double environmentScale;
        public double costScale;

        public double getSpeedScale() {
            return speedScale;
        }

        public void setSpeedScale(double speedScale) {
            this.speedScale = speedScale;
        }

        public double getEnvironmentScale() {
            return environmentScale;
        }

        public void setEnvironmentScale(double environmentScale) {
            this.environmentScale = environmentScale;
        }

        public double getCostScale() {
            return costScale;
        }

        public void setCostScale(double costScale) {
            this.costScale = costScale;
        }

        public routeSelectionValue(double speed, double environment, double cost) {

            speedScale = speed;
            environmentScale = environment;
            costScale = cost;
        }
    }


    public static String routeSelector(routeSelectionValue bus,routeSelectionValue train,routeSelectionValue walk,
                                       routeSelectionValue drive, routeSelectionValue bike, routeSelectionValue user ){
// The speed, cost and environment values of corresponding routes is given as input to this function.
        double busRouteValue;
        double trainRouteValue;
        double walkRouteValue;
        double driveRouteValue;
        double bikeRouteValue;
        double bestRoute;

        busRouteValue=(user.speedScale/10)*bus.speedScale+(user.environmentScale/10)*bus.environmentScale+(user.costScale/10)*bus.costScale;
        trainRouteValue=(user.speedScale/10)*train.speedScale+(user.environmentScale/10)*train.environmentScale+(user.costScale/10)*train.costScale;
        walkRouteValue=(user.speedScale/10)*walk.speedScale+(user.environmentScale/10)*walk.environmentScale+(user.costScale/10)*walk.costScale;
        driveRouteValue=(user.speedScale/10)*drive.speedScale+(user.environmentScale/10)*drive.environmentScale+(user.costScale/10)*drive.costScale;
        bikeRouteValue=(user.speedScale/10)*bike.speedScale+(user.environmentScale/10)*bike.environmentScale+(user.costScale/10)*bike.costScale;

        bestRoute= Math.max(busRouteValue, Math.max(trainRouteValue, Math.max(walkRouteValue,Math.max(driveRouteValue,bikeRouteValue))));
        // Selects the route with maximum route value

        // The following lines of code is just for printing the route values ( can be removed afterwards)
        System.out.println("Bus "+busRouteValue);
        System.out.println("Train "+trainRouteValue);
        System.out.println("Walk "+ walkRouteValue);
        System.out.println("Drive "+ driveRouteValue);
        System.out.println("Bike "+ bikeRouteValue);
        System.out.println("best "+ bestRoute);

        if (bestRoute == busRouteValue)
            return "Bus";
        if (bestRoute == trainRouteValue)
            return "Train";
        if (bestRoute == walkRouteValue)
            return "Walk";
        if (bestRoute == driveRouteValue)
            return "Drive";
        if (bestRoute == bikeRouteValue)
            return "Bike";
        else{
            //routeSelectionTest.function1(); // Test has not yet uploaded
            return "nothing";
        }

    }
}
