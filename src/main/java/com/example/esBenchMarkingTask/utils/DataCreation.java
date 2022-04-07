package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.GeoShapeFieldDoc;
import com.example.esBenchMarkingTask.model.ModelWithGeoPointLocation;
import com.example.esBenchMarkingTask.model.ModelWithGeoShapeLocation;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.geo.GeoShapeType;
import org.janusgraph.core.attribute.Geoshape;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the class that is generating the Documents
 */
public class DataCreation {
    public double upperBoundLatitude = 85.011;
    public double lowerBoundLatitude = 85.0511;
    public double lowerBoundLongitude = 180.0;
    public double upperBoundLongitude = 180.0;
    private int MAX_ZOOM_LEVELS = 15;
    public int docCount = 1_000_000;
    private List<? extends ModelWithGeoPointLocation> generatedGeoPointDocs;
    private List<? extends ModelWithGeoShapeLocation> generatedGeoShapeDocs;
    private static DataCreation dataCreationInstance;
    private List<String> tileIds;

    /**
     * Made it synchronized to make it thread safe and made it a singleton class to make sure only one general list is created. Made it static so that the getInstance class is accessible from the class itself and not the object.
     * @return
     */
    synchronized public static DataCreation getInstance()
    {
        if(dataCreationInstance == null){
            dataCreationInstance = new DataCreation();
        }
        return dataCreationInstance;
    }

    /**
     * This function is to get the generated documents. Used wildcard as it can be of any type GeoPointDoc, GeoShapeDoc, or TermQueryDoc which are all subclasses of ModelWithLocation
     * @return
     */
    public List<? extends ModelWithGeoPointLocation> getGeneratedGeoPointDocs() {
        return generatedGeoPointDocs;
    }

    public List<? extends ModelWithGeoShapeLocation> getGeneratedGeoShapeDocs(){
        return generatedGeoShapeDocs;
    }

    /**
     * This is the constructor used to initialize generated Docs
     */
    private DataCreation() {
        RandomDocsGenerator();
    }

    /**
     * Used to create a list of documents of length "docCount".
     * Methods called:
     * <ul>
     *     <li>generateCoordinates - Used to generate the location</li>
     *     <li>CalculateTilds</li>
     * </ul>
     */
    private void RandomDocsGenerator() {
        List<ModelWithGeoPointLocation> generalGeoPointList = new ArrayList<>();
        List<ModelWithGeoShapeLocation> generalGeoShapeList = new ArrayList<>();
        for(int i=0;i<docCount;i++){
            ModelWithGeoPointLocation modelGeoPointDoc = new GeoPointDoc();
            ModelWithGeoShapeLocation modelGeoShapeDoc = new GeoShapeFieldDoc();
            modelGeoPointDoc.setId(String.format("%d", i));
            modelGeoShapeDoc.setId(String.format("%d", i));
            List<Double> coordinates = AuxiliaryFunction.generateCoordinates();
            GeoPoint geoPoint = new GeoPoint(coordinates.get(0), coordinates.get(1));
            modelGeoPointDoc.setLocation(geoPoint);
            Geoshape geoShape = Geoshape.point(coordinates.get(0), coordinates.get(1));
            List<String> tileIds = AuxiliaryFunction.calculateTileIds(coordinates.get(0), coordinates.get(1));
            modelGeoPointDoc.setTileIds(tileIds);
            modelGeoShapeDoc.setTileIds(tileIds);
            generalGeoPointList.add(modelGeoPointDoc);
            generalGeoShapeList.add(modelGeoShapeDoc);
        }
        this.generatedGeoPointDocs = generalGeoPointList;
        this.generatedGeoShapeDocs = generalGeoShapeList;
    }
}
