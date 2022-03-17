package com.example.esBenchMarkingTask.service.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.QueryType;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.builders.EnvelopeBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoShapeQueryHandler implements QueryHandler{
    public static final String hostAndPort = "localhost:9200";
    public static final String geoShapeIndex = "test2geoshapes";
    @Override
    public void handleQuery(JSONObject query) throws IOException {
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

    @Override
    public QueryType getQueryingType() {
        return QueryType.GEO_SHAPE;
    }
}
