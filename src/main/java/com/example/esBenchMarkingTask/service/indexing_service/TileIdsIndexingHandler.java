package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.ModelWithGeoPointLocation;
import com.example.esBenchMarkingTask.model.TermQueryDoc;
import com.example.esBenchMarkingTask.repository.RepoTermQuery;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that creates and indexes TermQueryDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class TileIdsIndexingHandler implements IndexingTypeHandler {

    private DataCreation dataCreation = DataCreation.getInstance();
    @Autowired
    private RepoTermQuery repoTermQuery;

    /**
     * This has a Suppress warning as I am DownCasting but it should be fine as the superclass contains same fields as subclass and follows same pattern.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void indexDocs() {
        List<List<? extends ModelWithGeoPointLocation>> fullGeoPointDocumentList = dataCreation.getGeneratedGeoPointDocs();
        for(int i=0;i<10;i++){
            repoTermQuery.saveAll((List<TermQueryDoc>) fullGeoPointDocumentList.get(i));
        }
    }
    /**
     * This is used to get the ENUM value TILE_IDS
     * @return
     */
    @Override
    public IndexingType indexingType() {
        return IndexingType.TILE_IDS;
    }
}
