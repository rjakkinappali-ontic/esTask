package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.GeneralModelInterface;
import com.example.esBenchMarkingTask.model.GeoPointTask;
import com.example.esBenchMarkingTask.model.GeoShapeTask;
import com.example.esBenchMarkingTask.model.TermQueryTask;
import com.google.common.base.Joiner;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class Util {
    public static final double upperBoundLatitude = 85.011;
    private static final int MAX_ZOOM_LEVELS = 15;
    public static final double lowerBoundLatitude = 85.0511;
    public static final double lowerBoundLongitude = 180.0;
    public static final double upperBoundLongitude = 180.0;
    public int docCount = 10;
    private List<String> tileIds;
    private List<? extends GeneralModelInterface> generalList;

    private List<GeoPointTask> geoPointTask;
    private List<TermQueryTask> termQueryTask;
    private List<GeoShapeTask> geoShapeTask;

    public List<? extends GeneralModelInterface> getGeneralList() {
        return generalList;
    }

    public void setGeneralList(List<GeneralModelInterface> generalList) {
        this.generalList = generalList;
    }

    public void setGeneralList() {
        generateListOfDocuments();
    }

    public List<GeoPointTask> getGeoPointTask() {
        if (geoPointTask == null)
            setGeoPointTask();
        return geoPointTask;
    }

    public List<TermQueryTask> getTermQueryTask() {
        if (termQueryTask == null)
            setTermQueryTask();
        return termQueryTask;
    }

    public List<GeoShapeTask> getGeoShapeTask() {
        if (geoShapeTask == null)
            setGeoShapeTask();
        return geoShapeTask;
    }


    public void setGeoPointTask() {
        if (generalList == null)
            setGeneralList();
        this.geoPointTask = (List<GeoPointTask>) generalList;
    }

    public void setTermQueryTask() {
        if (generalList == null)
            setGeneralList();
        this.termQueryTask = (List<TermQueryTask>) generalList;
    }

    public void setGeoShapeTask() {
        if (generalList == null)
            setGeneralList();
        this.geoShapeTask = (List<GeoShapeTask>) generalList;
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
        double xCords = (random.nextFloat() * (lowerBoundLatitude + upperBoundLatitude)) - lowerBoundLatitude;
        double yCords = (random.nextFloat() * (lowerBoundLongitude + upperBoundLongitude)) - lowerBoundLongitude;
        coordinates.add(xCords);
        coordinates.add(yCords);
        return coordinates;
    }


    public void generateListOfDocuments() {
        List<GeneralModelInterface> listModel = new ArrayList<>();
        for (int i = 0; i < docCount; i++) {
            GeoPointTask model = new GeoPointTask();
            model.setId(String.format("%d", i));
            List<Double> coordinates = generateCoordinates();
            GeoPoint geoPoint = new GeoPoint(coordinates.get(0), coordinates.get(1));
            model.setLocation(geoPoint);
            model.setTileIds(calculateTilds(coordinates.get(0), coordinates.get(1)));
            listModel.add(model);
        }
        this.generalList = listModel;
    }
}

