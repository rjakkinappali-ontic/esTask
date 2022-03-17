package com.example.esBenchMarkingTask.service.query;

import com.example.esBenchMarkingTask.model.QueryType;

/**
 * This is a contract that will be used by TermQueryHandler, GeoShapeHandler, GeoPointHandler
 */
public interface QueryHandler {
    void handleQuery();

    QueryType getQueryingType();
}
