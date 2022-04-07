package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.GeoShapeQueryDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.ModelWithGeoPointLocation;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeQuery;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GeoShapeQueryIndexingHandler implements IndexingTypeHandler {

    private DataCreation dataCreation = DataCreation.getInstance();
    @Autowired
    private RepoGeoShapeQuery repoGeoShapeQuery;

    /**
     * This has a Suppress warning as I am DownCasting but it should be fine as the superclass contains same fields as subclass and follows same pattern.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void indexDocs() {
        List<List<? extends ModelWithGeoPointLocation>> fullGeoPointDocumentList = dataCreation.getGeneratedGeoPointDocs();
        for(int i=0;i<10;i++){
            repoGeoShapeQuery.saveAll((List<GeoShapeQueryDoc>) fullGeoPointDocumentList.get(i));
        }
    }

    /**
     * This is used to get the ENUM value GEO_SHAPE
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_SHAPE_QUERY;
    }
}
