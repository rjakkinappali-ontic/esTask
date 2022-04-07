package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;
/**
 * Document to be used for Benchmarking geoshape queries.
 * It has the following fields.
 * <ul>
 *     <li><b>String</b>: id</li>
 *     <li><b>GeoPoint</b>: Location</li>
 *     <li><b>List < String ></b>: TileIds</li>
 * </ul>
 */
@Document(indexName = "geoshapequerydocmilliontest1")
public class GeoShapeQueryDoc implements ModelWithGeoPointLocation {
    @Id
    private String id;

    @GeoPointField
    private GeoPoint Location;

    private List<String> tileIds;
    /**
     * Method to return the object of type GeoShapeDoc in type String
     * @return
     */
    @Override
    public String toString() {
        return "taskData{" +
                "id='" + id + '\'' +
                ", Location=" + Location +
                ", tileIds=" + tileIds +
                '}';
    }

    /**
     * Method to get the id of the GeoShape Document
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set the id of the GeoShape Document
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get the location of a GeoShape Document. Returns a type GeoPoint
     * @return
     */
    public GeoPoint getLocation() {
        return Location;
    }

    /**
     * Method to set the location of a GeoShape Document.
     * @param location
     */
    public void setLocation(GeoPoint location) {
        this.Location = location;
    }

    /**
     * Method to get the tile ids of a GeoShape Document.
     * @return
     */
    public List<String> getTileIds() {
        return tileIds;
    }

    /**
     * Method to set the tile ids of a GeoShape Document.
     * @param tileIds
     */
    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
}
