package com.example.esBenchMarkingTask.model;

import org.springframework.stereotype.Component;

@Component
public class ReturnIndexingType {
    public GeneralIndexModel getObject(String indexingType) {
        switch (IndexingTypes.valueOf(indexingType)) {
            case term:
                return new TermQueryTask();
            case geo_shape:
                return new GeoShapeTask();
            case geo_distance:
                return new GeoPointTask();
            default:
                System.out.println("Wrong Type");
                return null;
        }
    }
}
