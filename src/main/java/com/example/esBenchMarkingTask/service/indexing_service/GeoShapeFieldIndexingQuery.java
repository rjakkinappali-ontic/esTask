package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoShapeFieldDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeField;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoShapeFieldIndexingQuery implements IndexingTypeHandler {
    private DataCreation dataCreation = DataCreation.getInstance();
    @Autowired
    private RepoGeoShapeField repoGeoShapeField;
    @Override
    @SuppressWarnings("unchecked")
    public void indexDocs() {
        repoGeoShapeField.saveAll((List<GeoShapeFieldDoc>)dataCreation.getGeneratedGeoShapeDocs());
    }

    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_SHAPE_FIELD;
    }
}
