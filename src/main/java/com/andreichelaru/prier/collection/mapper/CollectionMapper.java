package com.andreichelaru.prier.collection.mapper;

import com.andreichelaru.prier.collection.Collection;
import com.andreichelaru.prier.collection.dto.request.CollectionRequest;
import com.andreichelaru.prier.collection.dto.response.CollectionResponse;
import com.andreichelaru.prier.product.Product;
import com.andreichelaru.prier.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CollectionMapper {
    private final ProductMapper productMapper;

    @Autowired
    public CollectionMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Collection toCollection(CollectionRequest collectionRequest) {
        Collection collection = new Collection();
        collection.setId(collectionRequest.getId());
        collection.setName(collectionRequest.getName());
        collection.setDescription(collectionRequest.getDescription());

        return collection;
    }

    public CollectionResponse toCollectionResponse(Collection collection) {
        CollectionResponse collectionResponse = new CollectionResponse();
        collectionResponse.setId(collection.getId());
        collectionResponse.setName(collection.getName());
        collectionResponse.setDescription(collection.getDescription());
        collectionResponse.setMetadata(collection.getMetadata());
        collectionResponse.setProducts(
                productMapper.toProductResponseList(new ArrayList<Product>(collection.getProducts()))
        );

        return collectionResponse;
    }

    public List<Collection> toCollectionList(List<CollectionRequest> collectionRequests) {
        return collectionRequests.stream().map(this::toCollection).toList();
    }

    public List<CollectionResponse> toCollectionResponseList(List<Collection> collections) {
        return collections.stream().map(this::toCollectionResponse).toList();
    }
}
