package com.example.esBenchMarkingTask.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.QueryType;
import com.example.esBenchMarkingTask.service.indexing.IndexingTypeHandler;
import com.example.esBenchMarkingTask.service.indexing.IndexingTypeHandlerRegistry;
import com.example.esBenchMarkingTask.service.query.QueryHandler;
import com.example.esBenchMarkingTask.service.query.QueryTypeRegistry;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.builders.EnvelopeBuilder;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

//    public void queryHandle(JSONObject query){
//        QueryType queryTypeEnum = QueryType.valueOf(query.getString("type"));
//        QueryTyp
//    }
    public void queryHandle(JSONObject query) throws IOException{
        QueryType queryTypeEnum = QueryType.valueOf(query.getString("type"));
        QueryHandler queryHandler = queryRegistry.getQueryHandler(queryTypeEnum);
        queryHandler.handleQuery(query);
    }



    private void handleGeoShapeQuery(JSONObject query) throws IOException {
        JSONArray locationArray = query.getJSONArray("Location");
        List<Double> coordinates = new ArrayList<>();
        for (int i = 0; i < locationArray.size(); i++) {
            coordinates.add(locationArray.getJSONObject(i).getDouble("X"));
            coordinates.add(locationArray.getJSONObject(i).getDouble("Y"));
        }
        Coordinate coordinateTopLeft = new Coordinate(coordinates.get(1), coordinates.get(0));
        Coordinate coordinateBottomRight = new Coordinate(coordinates.get(3), coordinates.get(2));
        EnvelopeBuilder shape = new EnvelopeBuilder(coordinateTopLeft, coordinateBottomRight);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.geoWithinQuery("Location", shape));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(geoShapeIndex);
        searchRequest.source(searchSourceBuilder);
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPort).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        System.out.println(searchHits.length);
        for (SearchHit i : searchHits) {
            System.out.println(i.toString());
        }
    }

    private void handleTermQuery(JSONObject query) throws IOException {
        String tileId = query.getString("tileId");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.termQuery("tileIds", tileId));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(termQueryIndex);
        searchRequest.source(searchSourceBuilder);
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPort).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        System.out.println(searchHits.length);
        for (SearchHit i : searchHits) {
            System.out.println(i.toString());
        }
    }
}
