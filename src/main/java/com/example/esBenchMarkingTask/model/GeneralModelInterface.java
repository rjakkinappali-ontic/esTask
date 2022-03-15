package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GeneralModelInterface {
    void setId(String format);

    void setLocation(GeoPoint geoPoint);

    void setTileIds(List<String> calculateTilds);
}
