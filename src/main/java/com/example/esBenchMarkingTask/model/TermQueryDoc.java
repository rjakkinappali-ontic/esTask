package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
/**
 * This a Document to be used for Benchmarking term queries.
 * It has the following fields.
 * <ul>
 *     <li><b>String</b>: id</li>
 *     <li><b>GeoPoint</b>: Location</li>
 *     <li><b>List < String ></b>: TileIds</li>
 * </ul>
 */
@Document(indexName = "testing3termquerys")
public class TermQueryDoc implements ModelWithLocation {

    @Id
    private String id;

    private GeoPoint location;

    private List<String> tileIds;

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
    /**
     * This is used to return the object of type TermQueryDoc in type String
     * @return
     */
    @Override
    public String toString() {
        return "TermQueryTask{" +
                "id='" + id + '\'' +
                ", Location=" + location +
                ", tileIds=" + tileIds +
                '}';
    }
}
