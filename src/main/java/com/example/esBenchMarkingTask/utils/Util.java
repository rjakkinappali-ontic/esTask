package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.GeneralIndexModel;
import com.example.esBenchMarkingTask.model.ReturnIndexingType;
import com.google.common.base.Joiner;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class Util {

    @Autowired
    private ReturnIndexingType returnIndexingType;

    @Autowired
    private GeneralIndexModel model;


    public int docCount = 10;
    private static final int MAX_ZOOM_LEVELS = 15;


    private List<String> tileIds;

    private List<GeneralIndexModel> listOfDocuments = null;



    public List<?> getListOfDocuments() {
        if (listOfDocuments == null)
            setListOfDocuments();
        return listOfDocuments;
    }

    public void setListOfDocuments() {
        this.listOfDocuments = generateListOfDocuments();
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

    private Object getY(Double i, Double y) {
        return (int) Math.floor(((y + 180) / 360) * Math.pow(2, i));
    }

    public List<Double> generateCoordinates() {
        List<Double> coordinates = new ArrayList<>();
        Random random = new Random();
        double xCords = (random.nextFloat() * (85.0511 + 85.011)) - 85.0511;
        double yCords = (random.nextFloat() * (180.0 + 180.0)) - 180.0;
        coordinates.add(xCords);
        coordinates.add(yCords);
        return coordinates;
    }


    public List<GeneralIndexModel> generateListOfDocuments() {
        List<GeneralIndexModel> listModel = new ArrayList<>();
        for (int i = 0; i < docCount; i++) {
            model.setId(String.format("%d", i));
            List<Double> coordinates = generateCoordinates();
            GeoPoint geoPoint = new GeoPoint(coordinates.get(0), coordinates.get(1));
            model.setLocation(geoPoint);
            model.setTileIds(calculateTilds(coordinates.get(0), coordinates.get(1)));
            listModel.add(model);
        }
        return listModel;
    }

}

