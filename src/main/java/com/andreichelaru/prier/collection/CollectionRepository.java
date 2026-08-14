package com.andreichelaru.prier.collection;

import com.andreichelaru.prier.collection.dto.response.CollectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> getCollectionByName(String name);
    boolean existsByName(String name);
}
