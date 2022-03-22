package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.GeoShapeDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoPoint;
import com.example.esBenchMarkingTask.repository.RepoGeoShape;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that creates and indexes GeoShapeDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class GeoShapeIndexingHandler implements IndexingTypeHandler {

    private DataCreation dataCreation = DataCreation.getInstance();
    @Autowired
    private RepoGeoShape repoGeoShape;

    /**
     * This has a Suppress warning as I am DownCasting but it should be fine as the superclass contains same fields as subclass and follows same pattern.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void indexDocs() {
        repoGeoShape.saveAll((List<GeoShapeDoc>)dataCreation.getGeneratedDocs());
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
