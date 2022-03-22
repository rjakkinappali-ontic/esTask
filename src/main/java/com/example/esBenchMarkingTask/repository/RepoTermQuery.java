package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.TermQueryDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTermQuery extends ElasticsearchRepository<TermQueryDoc, String> {
}
