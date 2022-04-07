package com.example.esBenchMarkingTask.service.indexing_service;

import com.example.esBenchMarkingTask.model.GeoShapeFieldDoc;
import com.example.esBenchMarkingTask.model.IndexingType;
import com.example.esBenchMarkingTask.model.ModelWithGeoShapeLocation;
import com.example.esBenchMarkingTask.repository.RepoGeoShapeField;
import com.example.esBenchMarkingTask.utils.DataCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoShapeFieldIndexingQuery implements IndexingTypeHandler {
    public static final int documentPartitions = 10;
    private DataCreation dataCreation = DataCreation.getInstance();
    @Autowired
    private RepoGeoShapeField repoGeoShapeField;
    @Override
    @SuppressWarnings("unchecked")
    public void indexDocs() {
        List<List<? extends ModelWithGeoShapeLocation>> fullDocumentList = dataCreation.getGeneratedGeoShapeDocs();
        for(int i = 0; i< documentPartitions; i++){
            repoGeoShapeField.saveAll((List<GeoShapeFieldDoc>) fullDocumentList.get(i));
        }
    }

    @Override
    public IndexingType indexingType() {
        return IndexingType.GEO_SHAPE_FIELD;
    }
}
