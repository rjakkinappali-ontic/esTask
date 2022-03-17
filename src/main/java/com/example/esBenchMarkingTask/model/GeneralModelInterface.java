package com.example.esBenchMarkingTask.model;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interface to represent a general index that will be used
 */
@Component
public interface GeneralModelInterface {
    /**
     * This is used to set the Id for the document, it has to be unique.
     * @param id
     */
    void setId(String id);
}
