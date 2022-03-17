package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoShapeDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
/**
 * This is a repository for GeoShapeDoc. This extends ElasticsearchRepository which helps it access it CRUD repository, this helps in using saveall in bulkwrite.
 */
public interface RepoGeoShapeQuery extends ElasticsearchRepository<GeoShapeDoc, String> {

}
