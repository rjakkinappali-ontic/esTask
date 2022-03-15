package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.TermQueryTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTermQuery extends ElasticsearchRepository<TermQueryTask, String> {

}