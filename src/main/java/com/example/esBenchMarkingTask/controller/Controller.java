package com.example.esBenchMarkingTask.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.esBenchMarkingTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {
    @Autowired
    private TaskService taskService;

    @GetMapping("/bulkWrite")
    public void bulkWriteDocs(@RequestBody String indexingType) {
        taskService.createIndex(indexingType);
    }

    @GetMapping("/query")
    public long termQuery(@RequestBody JSONObject query) throws IOException {
        long startTime = System.currentTimeMillis();
        taskService.handleQuery(query);
        long end = System.currentTimeMillis();
        return end - startTime;
    }
}
