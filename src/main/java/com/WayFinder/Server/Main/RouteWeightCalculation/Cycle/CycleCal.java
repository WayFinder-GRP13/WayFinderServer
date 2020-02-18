package com.WayFinder.Server.Main.RouteWeightCalculation.Cycle;

public class CycleCal {

    public long CO2CalCycle(long distance)
    {
        long emmissions_per_CycleMile = distance*(long)0.011;// 0.0110231

        return emmissions_per_CycleMile;

    }

}


