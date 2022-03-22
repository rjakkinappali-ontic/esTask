package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.ModelWithLocation;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public abstract class AbstractIndexHandler<T extends ModelWithLocation> implements IndexingTypeHandler<T>{
    private ElasticsearchRepository<T,String> repository;
    private DataCreation<T> dataCreation = new DataCreation();

    @Override
    public void indexDocs()
    {
        repository.saveAll(dataCreation.getGeneratedDocs());
    }
}
