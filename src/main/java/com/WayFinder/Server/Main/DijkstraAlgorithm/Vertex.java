package com.WayFinder.Server.Main.DijkstraAlgorithm;
//node
public class Vertex {
    final private String StopID;
    final private String StopName;
    final private int TransportType;
    final private double Latitude;
    final private double Longitude;


    public Vertex(String StopID, String StopName,int TransportType,double Latitude,double Longitude) {
        this.StopID = StopID;
        this.StopName = StopName;
        this.TransportType=TransportType;
        this.Latitude = Latitude;
        this.Longitude=Longitude;
    }
    public String getId() {
        return StopID;
    }

    public String getName() {
        return StopName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((StopID == null) ? 0 : StopID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (StopID == null) {
            if (other.StopID != null)
                return false;
        } else if (!StopID.equals(other.StopID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return StopName;
    }

    public int getTransportType() {
        return TransportType;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }
}
