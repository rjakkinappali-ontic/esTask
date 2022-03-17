package com.example.esBenchMarkingTask.service.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.QueryType;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoDistanceQueryHandler implements  QueryHandler{

    public static final String hostAndPort = "localhost:9200";
    public static final String geoPointsIndex = "testing1geopoints";
    public static final int radiusOfSearch = 20;

    @Override
    public void handleQuery(JSONObject query) throws IOException {
        JSONArray locationArray = query.getJSONArray("Location");
        List<Double> coordinates = new ArrayList<>();
        for (int i = 0; i < locationArray.size(); i++) {
            coordinates.add(locationArray.getJSONObject(i).getDouble("X"));
            coordinates.add(locationArray.getJSONObject(i).getDouble("Y"));
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        GeoDistanceQueryBuilder gdqb = new GeoDistanceQueryBuilder("Location");
        gdqb.point(coordinates.get(0), coordinates.get(1));
        gdqb.distance(radiusOfSearch, DistanceUnit.KILOMETERS);
        searchSourceBuilder.query(gdqb);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(geoPointsIndex);
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

    @Override
    public QueryType getQueryingType() {
        return QueryType.GEO_DISTANCE;
    }
}
