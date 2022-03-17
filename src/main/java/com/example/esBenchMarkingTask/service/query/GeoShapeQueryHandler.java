package com.example.esBenchMarkingTask.service.query;

import com.example.esBenchMarkingTask.model.QueryType;

public class GeoShapeQueryHandler implements QueryHandler{
    @Override
    public void handleQuery() {

    }

    @Override
    public QueryType getQueryingType() {
        return QueryType.GEO_SHAPE;
    }
}
