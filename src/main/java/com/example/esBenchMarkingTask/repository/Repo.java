package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeneralIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends ElasticsearchRepository<GeneralIndexModel, String>
{

}