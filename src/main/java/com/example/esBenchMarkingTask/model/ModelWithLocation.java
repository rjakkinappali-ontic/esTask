package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;

/**
 * This is an interface that is to be used by indexes that have to set location and set tile ids, and have fields called location and tile ids
 */
public interface ModelWithLocation extends GeneralModelInterface {

    void setLocation(GeoPoint geoPoint);

    void setTileIds(List<String> calculateTilds);
}
