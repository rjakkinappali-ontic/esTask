package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.*;
import com.google.common.base.Joiner;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a util class, that is used to side tasks that perform auxilary tasks.
 *
 */
@Component
public class AuxiliaryFunctions {
    public static final double upperBoundLatitude = 85.011;
    public static final double lowerBoundLatitude = 85.0511;
    public static final double lowerBoundLongitude = 180.0;
    public static final double upperBoundLongitude = 180.0;
    private static final int MAX_ZOOM_LEVELS = 15;
    public int docCount = 10;
    private List<String> tileIds;
    private List<? extends ModelWithLocation> generalList;

    private List<GeoPointDoc> geoPointDoc;
    private List<TermQueryDoc> termQueryDoc;
    private List<GeoShapeDoc> geoShapeDoc;



    /**
     * This is used to return the List of documents which is a general list that is to be used for all benchmarking query task that involves tile id and location
     *
     * @return
     */
    public List<? extends GeneralModelInterface> getGeneralList() {
        return generalList;
    }

    /**
     * This method calls generateListOfDocs() which will set the value for generalList.
     */
    public void setGeneralList() {
        generateListOfDocs();
    }

    /**
     * <ul>
     *     <li>Used to get the value of geoPointDoc</li>
     *     <li>This method calls setGeoPointDocs() if the value for geoPointDocs is not set. </li>
     *     <li>This is done so that all index which are mapped in the same way as GeoPointDoc can have the same set of documents. </li>
     * </ul>
     */
    public List<GeoPointDoc> getGeoPointDocs() {
        if (geoPointDoc == null)
            setGeoPointDocs();
        return geoPointDoc;
    }

    /**
     * <ul>
     *      <li>Used to get the value of termQueryDoc</li>
     *      <li>This method calls setTermQueryDocs() if the value for termQueryDoc is not set. </li>
     *      <li>This is done so that all index which are mapped in the same way as TermQueryDoc can have the same set of documents. </li>
     * </ul>
     */
    public List<TermQueryDoc> getTermQueryDocs() {
        if (termQueryDoc == null)
            setTermQueryDocs();
        return termQueryDoc;
    }

    /**
     * <ul>
     *      <li>Used to get the value of geoShapeDoc</li>
     *      <li>This method calls setGeoShapeDocs() if the value for geoShapeDoc is not set. </li>
     *      <li>This is done so that all index which are mapped in the same way as GeoShapeQueryDoc can have the same set of documents. </li>
     * </ul>
     */
    public List<GeoShapeDoc> getGeoShapeDocs() {
        if (geoShapeDoc == null)
            setGeoShapeDocs();
        return geoShapeDoc;
    }

    /**
     * <ul>
     *      <li><b>THE REASON I SUPPRESSED THESE TYPECAST WARNINGS WAS BECAUSE I NEEDED A LIST OF SPECIFIC TYPE OF GEOPOINTDOC AND THE GENERALLIST IS WILDCARD THAT EXTENDS THE SUPER CLASS OF GEOPOINTDOC. SO ITS FINE TO TYPECAST.</b></li>
     *      <li>Also this function is set geoPointDoc to be the same value as generalList and incase generalList is empty it calls setGeneralList.</li>
     * </ul>
     * */
    @SuppressWarnings("unchecked")
    public void setGeoPointDocs() {
        if (generalList == null)
            setGeneralList();
        this.geoPointDoc = (List<GeoPointDoc>) generalList;
    }
    /**
     * <ul>
     *      <li><b>THE REASON I SUPPRESSED THESE TYPECAST WARNINGS WAS BECAUSE I NEEDED A LIST OF SPECIFIC TYPE OF GEOPOINTDOC AND THE GENERALLIST IS WILDCARD THAT EXTENDS THE SUPER CLASS OF GEOPOINTDOC. SO ITS FINE TO TYPECAST.</b></li>
     *      <li>Also this function is set termQueryDoc to be the same value as generalList and incase generalList is empty it calls setGeneralList.</li>
     * </ul>
     * */
    @SuppressWarnings("unchecked")
    public void setTermQueryDocs() {
        if (generalList == null)
            setGeneralList();
        this.termQueryDoc = (List<TermQueryDoc>) generalList;
    }

    /**
     * <ul>
     *      <li><b>THE REASON I SUPPRESSED THESE TYPECAST WARNINGS WAS BECAUSE I NEEDED A LIST OF SPECIFIC TYPE OF GEOPOINTDOC AND THE GENERALLIST IS WILDCARD THAT EXTENDS THE SUPER CLASS OF GEOPOINTDOC. SO ITS FINE TO TYPECAST.</b></li>
     *      <li>Also this function is set geoShapeDoc to be the same value as generalList and incase generalList is empty it calls setGeneralList.</li>
     * </ul>
     * */
    @SuppressWarnings("unchecked")
    public void setGeoShapeDocs() {
        if (generalList == null)
            setGeneralList();
        this.geoShapeDoc = (List<GeoShapeDoc>) generalList;
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


    public void generateListOfDocs() {
        List<ModelWithLocation> listModel = new ArrayList<>();
        for (int i = 0; i < docCount; i++) {
            GeoPointDoc model = new GeoPointDoc();
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

