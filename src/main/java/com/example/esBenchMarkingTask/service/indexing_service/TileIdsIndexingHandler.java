package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoTermQuery;
import com.example.esBenchMarkingTask.utils.AuxiliaryFunctions;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TileIdsIndexingHandler implements IndexingTypeHandler {

    @Autowired
    private RepoTermQuery repoTermQuery;

    @Autowired
    private AuxiliaryFunctions util;

    /**
     * This is used to write the Index documents into the index.
     */
    @Override
    public void indexDocs() {
        repoTermQuery.saveAll(util.getTermQueryDocs());
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
