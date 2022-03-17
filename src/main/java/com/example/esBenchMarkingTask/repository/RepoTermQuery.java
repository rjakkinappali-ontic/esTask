package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.TermQueryDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
/**
 * This is a repository for TermQueryDoc. This extends ElasticsearchRepository which helps it access it CRUD repository, this helps in using saveall in bulkwrite.
 */
@Repository
public interface RepoTermQuery extends ElasticsearchRepository<TermQueryDoc, String> {

}