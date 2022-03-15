package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "test3termquerys")
public class TermQueryTask implements GeneralModelInterface {
    @Id
    private String id;

    private GeoPoint Location;

    private List<String> tileIds;

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

    @Override
    public String toString() {
        return "TermQueryTask{" +
                "id='" + id + '\'' +
                ", Location=" + Location +
                ", tileIds=" + tileIds +
                '}';
    }
}
