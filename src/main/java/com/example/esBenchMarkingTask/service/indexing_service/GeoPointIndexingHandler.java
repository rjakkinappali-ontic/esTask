package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoPoint;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;


/**
 * Service that creates and indexes GeoPointDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class GeoPointIndexingHandler extends AbstractIndexHandler<GeoPointDoc> {

    @Autowired
    public RepoGeoPoint repoGeoPoint;

    /**
     * This is used to get the ENUM value GEO_POINT
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_POINT;
    }
}
