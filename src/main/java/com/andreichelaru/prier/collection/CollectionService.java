package com.andreichelaru.prier.collection;

import com.andreichelaru.prier.collection.dto.request.CollectionRequest;
import com.andreichelaru.prier.collection.dto.response.CollectionResponse;

import java.util.List;

public interface CollectionService {

    List<CollectionResponse> getAllCollections();
    CollectionResponse getCollectionById(Long id);
    CollectionResponse getCollectionByName(String name);
    List<CollectionResponse> getCollectionsByProductId(Long productId);
    CollectionResponse createCollection(CollectionRequest collectionRequest);
    CollectionResponse updateCollection(CollectionRequest collectionRequest);
    CollectionResponse deleteCollectionById(Long id);
}
