package com.example.esBenchMarkingTask.service.indexing;

import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeQuery;
import com.example.esBenchMarkingTask.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * This is a service that performs wireDocs on GeoShapeDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class GeoShapeIndexingHandler implements IndexingTypeHandler {

    @Autowired
    private RepoGeoShapeQuery repoGeoShapeQuery;

    @Autowired
    private Util util;

    /**
     * This is used to write the GeoShapedocuments into the index.
     */
    @Override
    public void indexDocs() {
        repoGeoShapeQuery.saveAll(util.getGeoShapeDocs());
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
