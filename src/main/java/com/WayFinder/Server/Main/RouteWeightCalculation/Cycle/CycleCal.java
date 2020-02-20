package com.WayFinder.Server.Main.RouteWeightCalculation.Cycle;

public class CycleCal {

    public double CycleWeight(double distance) {
        double CycleW = CO2CalCycle(distance) + CycleTime(distance) + CycleCost(distance);
        return (CycleW);
    }


    public double CO2CalCycle(double distance) {
        double emmissions_per_CycleMile = distance * (double) 0.011;// 0.0110231

        return emmissions_per_CycleMile;

    }

    /*
     * how long it took to cycle
     * How long it takes {distance/speed(km/hr)} = time
     * Inputs --> distance
     * Returns -->  cycle time in hours
     */
    public double CycleTime(double distance) {
        double CycleTime = distance / (double) 22.00;
        return (CycleTime);
    }

    /*
     * Cost of using the Dublin Bike depending on how long it was in use
     * Inputs --> distance
     * Returns -->  Cost
     */
    public double CycleCost(double distance) {
        int timeItTook = (int)CycleTime(distance);


        // Truncate as we are only concered with the hours.
        int timeOnBike = timeItTook;

        if(timeOnBike>4)
        {

            return (moreThan4Hour(timeOnBike));
        }

        else {
            switch (timeOnBike) {

                case 1:
                    return (0.50);

                case 2:
                    return (1.50);


                case 3:

                    return (3.50);


                case 4:
                    return (6.50);

                default:
                    return(0.0);
            }
        }

    }

    /*
     * If time spent is more than 4 hours, (minus 4 hours) and multiply evry hour by0.50
     * Inputs --> total time spent using the bike > 4 hours
     * Returns -->  Cost
     */
    //if to check if time is more than 4 hours, (minus 4 hours) and multiply evry hour by0.50
    public double moreThan4Hour(int timeTotal) {
        //concate as we are only concered with the ours and not the mins
        int total = timeTotal;
        total = total - 4;
        double tripCost = total * 0.50;
        return ((double) tripCost);
    }

}
// speed --> 26 km/h



