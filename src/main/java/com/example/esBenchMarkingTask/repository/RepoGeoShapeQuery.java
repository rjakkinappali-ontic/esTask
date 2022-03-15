package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoShapeTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RepoGeoShapeQuery extends ElasticsearchRepository<GeoShapeTask, String> {

}
