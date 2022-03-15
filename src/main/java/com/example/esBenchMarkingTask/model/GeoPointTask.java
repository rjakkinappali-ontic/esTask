package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.util.List;

@Document(indexName = "test1geopoints")
public class GeoPointTask implements GeneralModelInterface{
    @Id
    private String id;

    @GeoPointField
    private GeoPoint Location;

    private List<String> tileIds;

    @Override
    public String toString() {
        return "taskData{" +
                "id='" + id + '\'' +
                ", Location=" + Location.toString() +
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
        return Location;
    }

    public void setLocation(GeoPoint location) {
        Location = location;
    }

    public List<String> getTileIds() {
        return tileIds;
    }

    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
}
