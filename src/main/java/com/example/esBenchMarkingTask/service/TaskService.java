package com.example.esBenchMarkingTask.service;

import com.example.esBenchMarkingTask.model.GeoPointTask;
import com.example.esBenchMarkingTask.model.GeoShapeTask;
import com.example.esBenchMarkingTask.model.TermQueryTask;
import com.example.esBenchMarkingTask.repository.RepoGeoPointQuery;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeQuery;
import com.example.esBenchMarkingTask.repository.RepoTermQuery;
import com.example.esBenchMarkingTask.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    @Autowired
    private RepoTermQuery repoTermQuery;

    @Autowired
    private RepoGeoPointQuery repoGeoPointQuery;

    @Autowired
    private RepoGeoShapeQuery repoGeoShapeQuery;

    @Autowired
    private Util util;

    public void createIndex(String indexingType) {
        switch(indexingType){
            case "term":repoTermQuery.saveAll(util.getTermQueryTask());
            break;
            case "geo_shape":repoGeoShapeQuery.saveAll(util.getGeoShapeTask());
            break;
            case "geo_point":repoGeoPointQuery.saveAll(util.getGeoPointTask());
            break;
        }
    }
}
