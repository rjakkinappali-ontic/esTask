package com.example.esBenchMarkingTask.model;


import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GeneralIndexModel {
    void setLocation(GeoPoint geoPoint);
    void setId(String format);
    void setTileIds(List<String> tileIds);
}
