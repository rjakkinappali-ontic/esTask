package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Repository for GeoPointDoc. This extends ElasticsearchRepository which helps it access it CRUD repository, this helps in using saveall in bulkwrite.
 */
public interface RepoGeoPointQuery extends ElasticsearchRepository<GeoPointDoc, String> {

}
