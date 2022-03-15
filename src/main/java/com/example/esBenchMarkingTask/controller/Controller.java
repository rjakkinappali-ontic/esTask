package com.example.esBenchMarkingTask.controller;

import com.example.esBenchMarkingTask.model.GeneralIndexModel;
import com.example.esBenchMarkingTask.service.TaskService;
import com.example.esBenchMarkingTask.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private TaskService taskService;

    @GetMapping("/bulkWrite")
    public void bulkWriteDocs(@RequestBody String indexingType) {
        List<GeneralIndexModel> documentList = new ArrayList<>();
        taskService.createIndex(indexingType);
    }
}
