package com.example.esBenchMarkingTask.service;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.QueryType;
import com.example.esBenchMarkingTask.service.indexing_service.IndexingTypeHandler;
import com.example.esBenchMarkingTask.service.indexing_service.IndexingTypeHandlerRegistry;
import com.example.esBenchMarkingTask.service.query_service.QueryHandler;
import com.example.esBenchMarkingTask.service.query_service.QueryTypeRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * The main service that connects to both indexing_sevices and query_services
 */
@Service
public class RepositoryService {

    public static final String hostAndPort = "localhost:9200";
    public static final String geoPointsIndex = "test1geopoints";
    public static final String geoShapeIndex = "test2geoshapes";
    public static final String termQueryIndex = "test3termquerys";
    public static final int radiusOfSearch = 20;


    @Autowired
    private IndexingTypeHandlerRegistry indexRegistry;

    @Autowired
    private QueryTypeRegistry queryRegistry;


    /**
     * This is the function that is used to write the documents into the mentioned index
     * @param indexingType
     */
    public void writeDocs(String indexingType) {
        IndexingType indexingTypeEnum = IndexingType.valueOf(indexingType);
        IndexingTypeHandler indexingTypeHandler = indexRegistry.getIndexingTypeHandler(indexingTypeEnum);
        indexingTypeHandler.indexDocs();
    }

    public void queryHandle(JSONObject query) throws IOException{
        QueryType queryTypeEnum = QueryType.valueOf(query.getString("type"));
        QueryHandler queryHandler = queryRegistry.getQueryHandler(queryTypeEnum);
        queryHandler.handleQuery(query);
    }

}
