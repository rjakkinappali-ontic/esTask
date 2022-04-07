package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoShapeType;

import java.util.List;

public interface ModelWithGeoShapeLocation extends GeneralModelInterface{
    void setLocation(GeoShapeType location);
    void setTileIds(List<String> TileIds);
}
