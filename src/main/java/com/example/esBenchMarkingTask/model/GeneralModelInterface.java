package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is a general model interface, to represent indexes that will be used
 */
@Component
public interface GeneralModelInterface {

    void setId(String id);
}
