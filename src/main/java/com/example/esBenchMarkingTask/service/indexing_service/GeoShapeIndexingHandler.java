package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoShapeDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
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

    protected GeoShapeIndexingHandler(ElasticsearchRepository<GeoShapeDoc, String> repository, DataCreation<GeoShapeDoc> dataCreation) {
        super(repository, dataCreation);
    }

    /**
     * This is used to get the ENUM value GEO_SHAPE
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_SHAPE;
    }
}
