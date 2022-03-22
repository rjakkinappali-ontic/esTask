package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoShapeDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import org.springframework.stereotype.Service;
/**
 * Service that creates and indexes GeoShapeDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class GeoShapeIndexingHandler extends AbstractIndexHandler<GeoShapeDoc> {

    /**
     * This is used to get the ENUM value GEO_SHAPE
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_SHAPE;
    }
}
