package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoPointTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RepoGeoPointQuery extends ElasticsearchRepository<GeoPointTask, String> {

}
