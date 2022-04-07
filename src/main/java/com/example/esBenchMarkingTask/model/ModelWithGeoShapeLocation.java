package com.example.esBenchMarkingTask.model;



import org.janusgraph.core.attribute.Geoshape;

import java.util.List;

public interface ModelWithGeoShapeLocation extends GeneralModelInterface{
    void setLocation(Geoshape location);
    void setTileIds(List<String> TileIds);
}
