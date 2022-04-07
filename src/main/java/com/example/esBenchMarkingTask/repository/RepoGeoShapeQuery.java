package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoShapeQueryDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGeoShapeQuery extends ElasticsearchRepository<GeoShapeQueryDoc, String> {
}
