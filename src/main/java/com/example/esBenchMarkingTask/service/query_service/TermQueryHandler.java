package com.example.esBenchMarkingTask.service.query_service;

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
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service that is used to handle term query for indexed of the type TermQueryDoc
 */
@Service
public class TermQueryHandler implements QueryHandler{
    public static final String hostAndPort = "localhost:9200";
    public static final String termQueryIndex = "testing3termquerys";

    /**
     * <ul>
     *     <li>This method handle a query and search for all TileIdss in the "testing3termquery index that are the same as the tileId provided in the JSONObject in the key tileId</li>
     *     <li>This throws IOException because of the search function</li>
     * </ul>
     * @param query
     * @throws IOException
     */
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
