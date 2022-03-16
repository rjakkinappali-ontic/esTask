package com.example.esBenchMarkingTask.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.repository.RepoGeoPointQuery;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeQuery;
import com.example.esBenchMarkingTask.repository.RepoTermQuery;
import com.example.esBenchMarkingTask.utils.Util;
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
public class TaskService {

    public static final String HOST_AND_PORT = "localhost:9200";
    public static final String geoPointsIndex = "test1geopoints";
    public static final String geoShapeIndex = "test2geoshapes";
    public static final String termQueryIndex = "test3termquerys";
    @Autowired
    private RepoTermQuery repoTermQuery;

    @Autowired
    private RepoGeoPointQuery repoGeoPointQuery;

    @Autowired
    private RepoGeoShapeQuery repoGeoShapeQuery;

    @Autowired
    private Util util;

    public void createIndex(String indexingType) {
        switch (indexingType) {
            case "term":
                repoTermQuery.saveAll(util.getTermQueryTask());
                break;
            case "geo_shape":
                repoGeoShapeQuery.saveAll(util.getGeoShapeTask());
                break;
            case "geo_point":
                repoGeoPointQuery.saveAll(util.getGeoPointTask());
                break;
        }
    }


    public void handleQuery(JSONObject query) throws IOException {
        String queryType = query.getString("type");
        switch (queryType) {
            case "term":
                handleTermQuery(query);
                break;
            case "geo_shape":
                handleGeoShapeQuery(query);
                break;
            case "geo_point":
                handleGeoPointQuery(query);
                break;
        }
    }

    private void handleGeoPointQuery(JSONObject query) throws IOException {
        JSONArray locationArray = query.getJSONArray("Location");
        List<Double> coordinates = new ArrayList<>();
        for (int i = 0; i < locationArray.size(); i++) {
            coordinates.add(locationArray.getJSONObject(i).getDouble("X"));
            coordinates.add(locationArray.getJSONObject(i).getDouble("Y"));
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        GeoDistanceQueryBuilder gdqb = new GeoDistanceQueryBuilder("Location");
        gdqb.point(coordinates.get(0), coordinates.get(1));
        gdqb.distance(20, DistanceUnit.KILOMETERS);
        searchSourceBuilder.query(gdqb);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(geoPointsIndex);
        searchRequest.source(searchSourceBuilder);
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(HOST_AND_PORT).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        System.out.println(searchHits.length);
        for (SearchHit i : searchHits) {
            System.out.println(i.toString());
        }
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
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(HOST_AND_PORT).build();
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
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(HOST_AND_PORT).build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        System.out.println(searchHits.length);
        for (SearchHit i : searchHits) {
            System.out.println(i.toString());
        }
    }
}
