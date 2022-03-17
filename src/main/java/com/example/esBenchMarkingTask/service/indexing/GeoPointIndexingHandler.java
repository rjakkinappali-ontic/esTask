package com.example.esBenchMarkingTask.service.indexing;

import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoPointQuery;
import com.example.esBenchMarkingTask.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * This is a service that performs wireDocs on GeoPointDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class GeoPointIndexingHandler implements IndexingTypeHandler {
    @Autowired
    private RepoGeoPointQuery repoGeoPointQuery;

    @Autowired
    private Util util;

    /**
     * This is used to write the GeoPoint documents into the index.
     */
    @Override
    public void indexDocs() {
        repoGeoPointQuery.saveAll(util.getGeoPointDocs());
    }

    /**
     * This is used to get the ENUM value GEO_POINT
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_POINT;
    }
}
