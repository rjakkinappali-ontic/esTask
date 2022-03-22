package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoPointQuery;
import com.example.esBenchMarkingTask.utils.AuxiliaryFunctions;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RepoGeoPointQuery repoGeoPointQuery;

    @Autowired
    private AuxiliaryFunctions util;

//    /**
//     * This is used to write the GeoPoint documents into the index.
//     */
//    @Override
//    public void indexDocs() {
//        repoGeoPointQuery.saveAll(util.getGeoPointDocs());
//    }

    /**
     * This is used to get the ENUM value GEO_POINT
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_POINT;
    }
}
