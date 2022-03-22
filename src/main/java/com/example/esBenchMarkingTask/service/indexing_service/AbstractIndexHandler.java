package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.ModelWithLocation;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public abstract class AbstractIndexHandler<T extends ModelWithLocation> implements IndexingTypeHandler<T>{
    @Autowired
    private final ElasticsearchRepository<T,String> repository;
    private final DataCreation<T> dataCreation = new DataCreation();

    protected AbstractIndexHandler(ElasticsearchRepository<T, String> repository) {
        this.repository = repository;
    }


    @Override
    public void indexDocs()
    {
        repository.saveAll(dataCreation.getGeneratedDocs());
    }
}
