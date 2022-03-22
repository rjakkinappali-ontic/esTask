package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.TermQueryDoc;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
/**
 * Service that creates and indexes TermQueryDoc.
 * It has the following methods.
 * <ul>
 *     <li>indexDocs</li>
 *     <li>indexType - which is used by IndexingTypeHandlerRegistry to set the map of indexing type and handler.</li>
 * </ul>
 */
@Service
public class TileIdsIndexingHandler extends AbstractIndexHandler<TermQueryDoc> {

    protected TileIdsIndexingHandler(ElasticsearchRepository<TermQueryDoc, String> repository, DataCreation<TermQueryDoc> dataCreation) {
        super(repository, dataCreation);
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
