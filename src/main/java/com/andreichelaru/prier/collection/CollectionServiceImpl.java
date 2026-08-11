package com.andreichelaru.prier.collection;

import com.andreichelaru.prier.collection.dto.request.CollectionRequest;
import com.andreichelaru.prier.collection.dto.response.CollectionResponse;
import com.andreichelaru.prier.collection.mapper.CollectionMapper;
import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.common.exceptions.ResourceNotFoundException;
import com.andreichelaru.prier.product.Product;
import com.andreichelaru.prier.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;
    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionServiceImpl.class);

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper, ProductRepository productRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<CollectionResponse> getAllCollections() {
        LOGGER.debug("Retrieving all collections");
        List<Collection> collections = collectionRepository.findAll();

        return collectionMapper.toCollectionResponseList(collections);
    }

    @Override
    public CollectionResponse getCollectionById(Long id) {
        LOGGER.debug("Retrieving collection by id {}", id);
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_ID", "Collection with id " + id + " not found")));

        LOGGER.debug("Returning collection with id {}", id);
        return collectionMapper.toCollectionResponse(collection.get());
    }

    @Override
    public CollectionResponse getCollectionByName(String name) {
        LOGGER.debug("Retrieving collection by name {}", name);
        Optional<Collection> collection = collectionRepository.getCollectionByName(name);
        if (collection.isEmpty())
            throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_NAME", "Collection with name " + name + " not found")));

        return collectionMapper.toCollectionResponse(collection.get());
    }

    @Override
    public List<CollectionResponse> getCollectionsByProductId(Long productId) {
        LOGGER.debug("Retrieving collections by product id {}", productId);
        LOGGER.debug("Retrieving product by id {}", productId);
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty())
            throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_ID", "Product with id " + productId + " not found")));

        return collectionMapper.toCollectionResponseList(new ArrayList<>(product.get().getCollections()));
    }

    @Override
    public CollectionResponse createCollection(CollectionRequest collectionRequest) {
        LOGGER.debug("Creating collection {}", collectionRequest);

        if (collectionRequest.getName() == null || collectionRequest.getName().isEmpty()) {
            throw new BusinessException(List.of(new ErrorModel("NO_NAME", "The collection to be created has no name")));
        }

        if (collectionRepository.existsByName(collectionRequest.getName())) {
            throw new BusinessException(List.of(new ErrorModel("NAME_EXISTS", "Collection already exists")));
        }

        Collection collection = collectionRepository.save(collectionMapper.toCollection(collectionRequest));

        LOGGER.debug("Collection created {}", collection);
        return collectionMapper.toCollectionResponse(collection);
    }

    @Override
    public CollectionResponse updateCollection(CollectionRequest collectionRequest) {
        LOGGER.debug("Updating collection {}", collectionRequest);
        if (collectionRepository.existsByName(collectionRequest.getName()))
            throw new BusinessException(List.of(new ErrorModel("INVALID_NAME", "Collection name already exists")));
        Optional<Collection> collection = collectionRepository.findById(collectionRequest.getId());
        if (collection.isEmpty())
            throw new BusinessException(List.of(new  ErrorModel("INVALID_ID", "Collection with id " + collectionRequest.getId() + " not found")));

        Collection collectionEntity = collection.get();
        collectionEntity.setName(collectionRequest.getName());
        collectionEntity.setDescription(collectionRequest.getDescription());

        LOGGER.debug("Collection updated {}", collectionEntity);
        return collectionMapper.toCollectionResponse(collectionRepository.save(collectionEntity));
    }

    @Override
    public CollectionResponse deleteCollectionById(Long id) {
        LOGGER.debug("Deleting collection by id {}", id);
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new BusinessException(List.of(new ErrorModel("INVALID_ID", "Collection with id " + id + " not found")));

        collectionRepository.deleteById(id);

        LOGGER.debug("Collection deleted {}", collection.get());
        return collectionMapper.toCollectionResponse(collection.get());
    }
}
