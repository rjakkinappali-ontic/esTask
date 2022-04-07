package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;
/**
 * Document to be used for Benchmarking term queries.
 * It has the following fields.
 * <ul>
 *     <li><b>String</b>: id</li>
 *     <li><b>GeoPoint</b>: Location</li>
 *     <li><b>List < String ></b>: TileIds</li>
 * </ul>
 */
@Document(indexName = "termquerydocmilliontest1")
public class TermQueryDoc implements ModelWithGeoPointLocation {

    @Id
    private String id;

    @GeoPointField
    private GeoPoint Location;

    private List<String> tileIds;

    /**
     * Method to return the id of TermQuery Document
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set the id of TermQuery Document
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get the location of type GeoPoint from a TermQuery Document
     * @return
     */
    public GeoPoint getLocation() {
        return Location;
    }

    /**
     * Method to set the location of a TermQuery Document
     * @param location
     */
    public void setLocation(GeoPoint location) {
        this.Location = location;
    }

    /**
     * Method to get TileIds of a TermQuery Document
     * @return
     */
    public List<String> getTileIds() {
        return tileIds;
    }

    /**
     * Method to set TileIds of a TermQuery Document
     * @param tileIds
     */
    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
    /**
     * Method to return the object of type TermQueryDoc in type String
     * @return
     */
    @Override
    public String toString() {
        return "TermQueryTask{" +
                "id='" + id + '\'' +
                ", Location=" + Location +
                ", tileIds=" + tileIds +
                '}';
    }
}
