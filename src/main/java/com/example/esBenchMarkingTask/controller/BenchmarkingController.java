package com.example.esBenchMarkingTask.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * This contains methods for benchmarking various ES search queries
 */
@RestController
@RequestMapping(path="benchmarking")
public class BenchmarkingController {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * Writes docs in ES index.
     * The types of indexes supported.
     * <ul>
     *     <li>Term</li>
     *     <li>Geo shape</li>
     *     <li>Geo distance</li>
     * </ul>
     *
     * @param indexingType The type of index
     */
    @PostMapping("/bulkWrite")
    public void bulkWriteDocs(@RequestParam(value = "indexType") String indexingType) {
        repositoryService.writeDocs(indexingType);
    }

    /**
     * Used to call ES queries and measure their time.
     * The types of indexes supported.
     * <ul>
     *     <li>Term</li>
     *     <li>Geo shape</li>
     *     <li>Geo point</li>
     * </ul>
     * @param query
     * @return
     * @throws IOException
     */

    @GetMapping("/query")
    public long termQuery(@RequestBody JSONObject query) throws IOException {
        long startTime = System.currentTimeMillis();
        repositoryService.handleQuery(query);
        long end = System.currentTimeMillis();
        return end - startTime;
    }
}
