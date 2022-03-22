package com.example.esBenchMarkingTask.utils;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to execute auxiliary function that are used for document generation.
 */
public class AuxiliaryFunction {
    public static double upperBoundLatitude = 85.011;
    public static double lowerBoundLatitude = 85.0511;
    public static double lowerBoundLongitude = 180.0;
    public static double upperBoundLongitude = 180.0;
    private static int MAX_ZOOM_LEVELS = 15;
    public int docCount = 10;
    private static List<String> tileIds;

    /**
     * This method is called to generate the locations of type GeoPoint.
     * @return
     */
    public static List<Double> generateCoordinates() {
        List<Double> coordinates = new ArrayList<>();
        Random random = new Random();
        double xCords = (random.nextFloat() * (lowerBoundLatitude + upperBoundLatitude)) - lowerBoundLatitude;
        double yCords = (random.nextFloat() * (lowerBoundLongitude + upperBoundLongitude)) - lowerBoundLongitude;
        coordinates.add(xCords);
        coordinates.add(yCords);
        return coordinates;
    }
    /**
     * This method is called to generate the locations of type GeoPoint.
     * @return
     */
    public static List<String> calculateTileIds(Double lat, Double lan) {
        if (lat == null || lan == null) {
            return null;
        }
        tileIds = new ArrayList<>();
        for (int i = 0; i < MAX_ZOOM_LEVELS; i++) {
            tileIds.add(Joiner.on("_").join(i, getY((double) i, lat), getX((double) i, lan)));
        }
        return tileIds;
    }
    /**
     * This method is called to generate the X value used to calculate TileIds.
     * @return
     */
    private static Object getX(Double i, Double x) {
        return (int) Math.floor(
                ((1 -
                        Math.log(Math.tan((x * Math.PI) / 180) + 1 / Math.cos((x * Math.PI) / 180)) / Math.PI) /
                        2) *
                        Math.pow(2, i)
        );
    }
    /**
     * This method is called to generate the Y value used to calculate TileIds.
     * @return
     */
    private static Object getY(Double i, Double y) {
        return Math.floor(((y + 180) / 360) * Math.pow(2, i));
    }
}
