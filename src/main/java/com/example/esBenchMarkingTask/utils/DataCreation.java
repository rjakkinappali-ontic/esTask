package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.ModelWithLocation;
import com.google.common.base.Joiner;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataCreation {
    public double upperBoundLatitude = 85.011;
    public double lowerBoundLatitude = 85.0511;
    public double lowerBoundLongitude = 180.0;
    public double upperBoundLongitude = 180.0;
    private int MAX_ZOOM_LEVELS = 15;
    public int docCount = 10;
    private List<? extends ModelWithLocation> generatedDocs;
    private static DataCreation dataCreationInstance;
    private List<String> tileIds;

    synchronized public static DataCreation getInstance()
    {
        if(dataCreationInstance == null){
            dataCreationInstance = new DataCreation();
        }
        return dataCreationInstance;
    }


    public List<? extends ModelWithLocation> getGeneratedDocs() {
        return generatedDocs;
    }

    public DataCreation() {
        this.generatedDocs = RandomDocsGenerator();
    }


    private List<? extends ModelWithLocation> RandomDocsGenerator() {
        List<ModelWithLocation> generalList = new ArrayList<>();
        for(int i=0;i<docCount;i++){
            ModelWithLocation model = new GeoPointDoc();
            model.setId(String.format("%d", i));
            List<Double> coordinates = generateCoordinates();
            GeoPoint geoPoint = new GeoPoint(coordinates.get(0), coordinates.get(1));
            model.setLocation(geoPoint);
            model.setTileIds(calculateTilds(coordinates.get(0), coordinates.get(1)));
            generalList.add(model);
        }
        return generalList;
    }

    public List<Double> generateCoordinates() {
        List<Double> coordinates = new ArrayList<>();
        Random random = new Random();
        double xCords = (random.nextFloat() * (lowerBoundLatitude + upperBoundLatitude)) - lowerBoundLatitude;
        double yCords = (random.nextFloat() * (lowerBoundLongitude + upperBoundLongitude)) - lowerBoundLongitude;
        coordinates.add(xCords);
        coordinates.add(yCords);
        return coordinates;
    }

    public List<String> calculateTilds(Double lat, Double lan) {
        if (lat == null || lan == null) {
            return null;
        }
        this.tileIds = new ArrayList<>();
        for (int i = 0; i < MAX_ZOOM_LEVELS; i++) {
            tileIds.add(Joiner.on("_").join(i, getY((double) i, lat), getX((double) i, lan)));
        }
        return this.tileIds;
    }

    private Object getX(Double i, Double x) {
        return (int) Math.floor(
                ((1 -
                        Math.log(Math.tan((x * Math.PI) / 180) + 1 / Math.cos((x * Math.PI) / 180)) / Math.PI) /
                        2) *
                        Math.pow(2, i)
        );
    }

    private Integer getY(Double i, Double y) {
        return (int) Math.floor(((y + 180) / 360) * Math.pow(2, i));
    }

}
