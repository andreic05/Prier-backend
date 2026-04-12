package com.andreichelaru.prier.product;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> getProductByName(String name);
    boolean existsByName(String name);
}
