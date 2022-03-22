package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.ModelWithLocation;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public abstract class AbstractIndexHandler<T extends ModelWithLocation> implements IndexingTypeHandler{
    private ElasticsearchRepository<T,String> repository;
    private DataCreation<T> dataCreation;

    @Override
    void indexDocs()
    {
        repository.saveAll(dataCreation.getGeneratedDocs());
    }
}