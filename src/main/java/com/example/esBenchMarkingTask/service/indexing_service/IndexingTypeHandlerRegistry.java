package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.IndexingType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the main registry that is used to get the different handlers like GeoPointHandler, GeoShapeHandler, and TermQueryHandler.
 */
@Service
public class IndexingTypeHandlerRegistry {

    private Map<IndexingType, IndexingTypeHandler> typeVsHandler = new HashMap();


    public IndexingTypeHandlerRegistry(List<IndexingTypeHandler> handlers) {
        for (IndexingTypeHandler handler : handlers) {
            typeVsHandler.put(handler.indexingType(), handler);
        }
    }

    public IndexingTypeHandler getIndexingTypeHandler(IndexingType indexingType) {
        return typeVsHandler.get(indexingType);
    }
}
