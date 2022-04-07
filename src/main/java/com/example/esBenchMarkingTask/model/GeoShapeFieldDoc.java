package com.example.esBenchMarkingTask.model;


import org.elasticsearch.common.geo.GeoShapeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoShapeField;

import java.util.List;

@Document(indexName = "geoshapefielddocmillion")
public class GeoShapeFieldDoc implements ModelWithGeoShapeLocation{

    @Id
    private String id;

    @GeoShapeField
    private GeoShapeType Location;

    private List<String> tileIds;

    @Override
    public String toString() {
        return "GeoShapeFieldDoc{" +
                "id='" + id + '\'' +
                ", Location=" + Location +
                ", tileIds=" + tileIds +
                '}';
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public GeoShapeType getLocation() {
        return Location;
    }

    @Override
    public void setLocation(GeoShapeType location) {
        Location = location;
    }

    public List<String> getTileIds() {
        return tileIds;
    }

    @Override
    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }
}
