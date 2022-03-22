package com.example.esBenchMarkingTask.repository;

import com.example.esBenchMarkingTask.model.GeoPointDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGeoPoint extends ElasticsearchRepository<GeoPointDoc, String> {
}
