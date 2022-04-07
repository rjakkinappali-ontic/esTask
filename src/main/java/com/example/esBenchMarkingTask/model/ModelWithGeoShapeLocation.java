package com.example.esBenchMarkingTask.model;



import org.elasticsearch.common.geo.GeoShapeType;
import org.janusgraph.core.attribute.Geoshape;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPoint;

import java.util.List;

public interface ModelWithGeoShapeLocation extends GeneralModelInterface{
    void setLocation(GeoJsonPoint location);
    void setTileIds(List<String> TileIds);
}
