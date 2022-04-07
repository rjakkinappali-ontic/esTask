package com.example.esBenchMarkingTask.service.query_service;

import com.example.esBenchMarkingTask.model.QueryType;
import org.springframework.stereotype.Service;


@Service
public class GeoShapeGeoDistanceQueryHandler extends AbstractGeoDistanceQueryHandler{
    @Override
    public QueryType getQueryingType() {
        return QueryType.GEO_SHAPE_GEO_DISTANCE_QUERY;
    }
}
