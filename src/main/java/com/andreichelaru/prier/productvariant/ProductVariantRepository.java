package com.andreichelaru.prier.productvariant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository  extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> getProductVariantsByProductId(Long productId);
    long deleteByProductId(Long productId);
}