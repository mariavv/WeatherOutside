package com.maria.weatheroutside.model.network;

public class Coordinates {

    private static double latitude = 0;
    private static double longitude = 0;

    public static void setCoordinates(double latitude, double longitude) {
        Coordinates.latitude = latitude;
        Coordinates.longitude = longitude;
    }

    public static double getLatitude() {
        return Coordinates.latitude;
    }

    public static double getLongitude() {
        return Coordinates.longitude;
    }
}
