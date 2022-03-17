package com.example.esBenchMarkingTask.service.query;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.QueryType;

import java.io.IOException;

/**
 * This is a contract that will be used by TermQueryHandler, GeoShapeHandler, GeoPointHandler
 */
public interface QueryHandler {
    void handleQuery(JSONObject query) throws IOException;

    QueryType getQueryingType();
}
