package com.andreichelaru.prier.product;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> getProductById(Long id);
    Optional<Product> getProductByName(String name);
}
