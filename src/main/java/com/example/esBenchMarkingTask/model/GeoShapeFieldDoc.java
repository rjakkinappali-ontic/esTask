package com.example.esBenchMarkingTask.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoShapeField;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPoint;

import java.util.List;

@Document(indexName = "geoshapefielddocmilliontest5")
public class GeoShapeFieldDoc implements ModelWithGeoShapeLocation{

    @Id
    private String id;

    @GeoShapeField
    private GeoJsonPoint Location;

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

    public GeoJsonPoint getLocation() {
        return Location;
    }

    @Override
    public void setLocation(GeoJsonPoint location) {
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
