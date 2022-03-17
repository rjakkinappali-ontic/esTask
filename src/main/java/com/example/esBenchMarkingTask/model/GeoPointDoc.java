package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;

/**
 * This a Document to be used for Benchmarking geopoint queries.
 * It has the following fields.
 * <ul>
 *     <li><b>String</b>: id</li>
 *     <li><b>GeoPoint</b>: Location</li>
 *     <li><b>List < String ></b>: TileIds</li>
 * </ul>
 */
@Document(indexName = "testing1geopoints")
public class GeoPointDoc implements ModelWithLocation {
    @Id
    private String id;

    @GeoPointField
    private GeoPoint location;

    private List<String> tileIds;

    /**
     * This is used to return the object of type GeoPointDoc in type String
     * @return
     */
    @Override
    public String toString() {
        return "taskData{" +
                "id='" + id + '\'' +
                ", Location=" + location.toString() +
                ", tileIds=" + tileIds +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public List<String> getTileIds() {
        return tileIds;
    }

    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
}
