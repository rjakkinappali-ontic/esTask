package com.example.esBenchMarkingTask.service;

import com.example.esBenchMarkingTask.model.GeneralIndexModel;
import com.example.esBenchMarkingTask.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private Repo repo;


    public void createIndex(List<GeneralIndexModel> modelList) {
        repo.saveAll(modelList);
    }
}
