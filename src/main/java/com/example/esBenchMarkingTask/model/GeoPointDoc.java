package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;

/**
 * Document to be used for Benchmarking geopoint queries.
 * It has the following fields.
 * <ul>
 *     <li><b>String</b>: id</li>
 *     <li><b>GeoPoint</b>: Location</li>
 *     <li><b>List < String ></b>: TileIds</li>
 * </ul>
 */
@Document(indexName = "geopointdocmilliontest1")
public class GeoPointDoc implements ModelWithGeoPointLocation {
    @Id
    private String id;

    @GeoPointField
    private GeoPoint Location;

    private List<String> tileIds;

    /**
     * Method used to return the object of type GeoPointDoc in type String
     * @return
     */
    @Override
    public String toString() {
        return "taskData{" +
                "id='" + id + '\'' +
                ", Location=" + Location.toString() +
                ", tileIds=" + tileIds +
                '}';
    }

    /**
     * Method used to get the id of a GeoPoint Document
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Method used to set the id of a GeoPoint Document
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method used to get the coordinates of the GeoPoint Document
     * @return
     */
    public GeoPoint getLocation() {
        return Location;
    }

    /**
     * Method used to set the coordinates of the GeoPoint Document
     * @param location
     */
    public void setLocation(GeoPoint location) {
        this.Location = location;
    }

    /**
     * Method to get the list of tileIds of the GeoPoint Document
     * @return
     */
    public List<String> getTileIds() {
        return tileIds;
    }

    /**
     * Method to set the list of tileIds of the GeoPoint Document
     * @param tileIds
     */
    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
}
