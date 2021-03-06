package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.IndexingType;

/**
 * This is a contract used by GeoPointHandler, GeoShapeHandler, and TileIdsHandler to wireDocs.
 */
public interface IndexingTypeHandler{

    void indexDocs();

    IndexingType indexingType();
}