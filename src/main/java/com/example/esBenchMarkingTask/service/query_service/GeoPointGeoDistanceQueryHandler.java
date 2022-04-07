package com.example.esBenchMarkingTask.service.query_service;

import com.example.esBenchMarkingTask.model.QueryType;
import org.springframework.stereotype.Service;

/**
 * Service that is used to handle Geo_Distance query for indexed of the type GeoPointDoc
 */
@Service
public class GeoPointGeoDistanceQueryHandler extends AbstractGeoDistanceQueryHandler{

    public static final String hostAndPort = "localhost:9200";
    public static final int radiusOfSearch = 20;

//    /**
//     * <ul>
//     *     <li>This method handle a query and search for all GeoPoints in the "testing1geopoint index that are in a circle of radius of 20Km from a location provided in the JSONObject Location</li>
//     *     <li>This throws IOException because of the search function</li>
//     * </ul>
//     * @param query
//     * @throws IOException
//     */
//    @Override
//    public void handleQuery(JSONObject query) throws IOException {
//        JSONArray locationArray = query.getJSONArray("Location");
//        List<Double> coordinates = new ArrayList<>();
//        for (int i = 0; i < locationArray.size(); i++) {
//            coordinates.add(locationArray.getJSONObject(i).getDouble("X"));
//            coordinates.add(locationArray.getJSONObject(i).getDouble("Y"));
//        }
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        GeoDistanceQueryBuilder gdqb = new GeoDistanceQueryBuilder("Location");
//        gdqb.point(coordinates.get(0), coordinates.get(1));
//        gdqb.distance(radiusOfSearch, DistanceUnit.KILOMETERS);
//        searchSourceBuilder.query(gdqb);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices(query.getString("index"));
//        searchRequest.source(searchSourceBuilder);
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPort).build();
//        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
//        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//        SearchHit[] searchHits = response.getHits().getHits();
//        System.out.println(searchHits.length);
//        for (SearchHit i : searchHits) {
//            System.out.println(i.toString());
//        }
//    }

    @Override
    public QueryType getQueryingType() {
        return QueryType.GEO_POINT_GEO_DISTANCE_QUERY;
    }
}
