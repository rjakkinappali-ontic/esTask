package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;

/**
 * Interface that is to be used by indexes that have to set location and set tile ids, and have fields called location and tile ids
 */
public interface ModelWithGeoPointLocation extends GeneralModelInterface {

    /**
     * Method that is to be used by classes for setting locations of type GeoPoint
     * @param geoPoint
     */
    void setLocation(GeoPoint geoPoint);

    /**
     * Method that is to be used by classes for setting Tile Ids (plural) of type String
     * @param tileIds
     */
    void setTileIds(List<String> tileIds);
}
