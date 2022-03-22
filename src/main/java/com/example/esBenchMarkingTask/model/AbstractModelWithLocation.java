package com.example.esBenchMarkingTask.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public abstract class AbstractModelWithLocation<T extends ModelWithLocation> implements ModelWithLocation{
    private ElasticsearchRepository<T,String> repository;
}
