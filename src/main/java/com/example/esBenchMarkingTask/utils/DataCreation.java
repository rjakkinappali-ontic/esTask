package com.example.esBenchMarkingTask.utils;

import com.example.esBenchMarkingTask.model.ModelWithLocation;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;

public class DataCreation {
    private final List<ModelWithLocation> generatedDocs;

    public DataCreation() {
        this.generatedDocs = RandomDocsGenerator();
    }

    private List<ModelWithLocation> RandomDocsGenerator() {
        for(int i=0;i)
    }
}
