package com.example.esBenchMarkingTask.service.query;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.model.QueryType;

public class TermQueryHandler implements QueryHandler{
    @Override
    public void handleQuery(JSONObject query) {

    }

    @Override
    public QueryType getQueryingType() {
        return QueryType.TERM;
    }
}
