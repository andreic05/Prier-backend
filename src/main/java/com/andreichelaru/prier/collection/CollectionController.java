package com.andreichelaru.prier.collection;

import com.andreichelaru.prier.collection.dto.request.CollectionRequest;
import com.andreichelaru.prier.collection.dto.response.CollectionResponse;
import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/collections")
public class CollectionController {
    private final CollectionService collectionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public ResponseEntity<List<CollectionResponse>> getAllCollections() {
        LOGGER.info("Retrieving all collections");

        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionResponse> getCollectionById(@PathVariable Long id) {
        LOGGER.info("Retrieving collection with id {}", id);

        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CollectionResponse>> getCollectionsBySearch(@RequestParam(required = false) String name, @RequestParam(required = false) Long productId) {
        LOGGER.info("GET /search with name {} and product {}", name, productId);
        if (name != null) return ResponseEntity.ok(List.of(collectionService.getCollectionByName(name)));
        if (productId != null) return ResponseEntity.ok(collectionService.getCollectionsByProductId(productId));

        throw new BusinessException(List.of(new ErrorModel("INVALID_SEARCH", "Provide query parameters")));
    }

    @PostMapping
    public ResponseEntity<CollectionResponse> createCollection(@Valid @RequestBody CollectionRequest collectionRequest) {
        LOGGER.info("Creating new collection {}", collectionRequest);

        return ResponseEntity.ok(collectionService.createCollection(collectionRequest));
    }

    @PutMapping
    public ResponseEntity<CollectionResponse> updateCollection(@Valid @NotNull @RequestBody CollectionRequest collectionRequest) {
        LOGGER.info("Updating collection with id {}", collectionRequest.getId());

        return ResponseEntity.ok(collectionService.updateCollection(collectionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CollectionResponse> deleteCollection(@PathVariable Long id) {
        LOGGER.info("Deleting collection with id {}", id);

        return ResponseEntity.ok(collectionService.deleteCollectionById(id));
    }
}
