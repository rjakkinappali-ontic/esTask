package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoShapeFieldDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGeoShapeField extends ElasticsearchRepository<GeoShapeFieldDoc, String> {
}
