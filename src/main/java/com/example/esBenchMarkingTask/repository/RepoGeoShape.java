package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoShapeDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGeoShape extends ElasticsearchRepository<GeoShapeDoc, String> {
}
