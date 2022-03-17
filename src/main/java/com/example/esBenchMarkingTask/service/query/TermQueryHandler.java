package com.example.esBenchMarkingTask.service.query;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.QueryType;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;

public class TermQueryHandler implements QueryHandler{
    public static final String hostAndPort = "localhost:9200";
    public static final String termQueryIndex = "testing3termquerys";
    @Override
    public void handleQuery(JSONObject query) throws IOException {
        String tileId = query.getString("tileId");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
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

    @Override
    public QueryType getQueryingType() {
        return QueryType.TERM;
    }
}
