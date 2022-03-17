package com.example.esBenchMarkingTask.service.query;

import com.example.esBenchMarkingTask.model.QueryType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryTypeRegistry {
    private Map<QueryType, QueryHandler> queryTypesMappedToHandler = new HashMap();
    public QueryTypeRegistry(List<QueryHandler> queryHandlers) {
        for (QueryHandler queryHandler : queryHandlers) {
            queryTypesMappedToHandler.put(queryHandler.getQueryingType(),queryHandler);
        }
    }

    public QueryHandler getQueryHandler(QueryType queryType)
    {
        return queryTypesMappedToHandler.get(queryType);
    }
}
