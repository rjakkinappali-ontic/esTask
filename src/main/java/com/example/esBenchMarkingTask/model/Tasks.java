package com.example.esBenchMarkingTask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "termquery")
public class Tasks implements GeneralIndexModel{
    @Id
    private String id;

    private String LocationX;

    private String LocationY;

    private List<String> tileIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationX() {
        return LocationX;
    }

    public void setLocationX(String locationX) {
        LocationX = locationX;
    }

    public String getLocationY() {
        return LocationY;
    }

    public void setLocationY(String locationY) {
        LocationY = locationY;
    }

    public List<String> getTileIds() {
        return tileIds;
    }

    public void setTileIds(List<String> tileIds) {
        this.tileIds = tileIds;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id='" + id + '\'' +
                ", LocationX='" + LocationX + '\'' +
                ", LocationY='" + LocationY + '\'' +
                ", tileIds=" + tileIds +
                '}';
    }
}
