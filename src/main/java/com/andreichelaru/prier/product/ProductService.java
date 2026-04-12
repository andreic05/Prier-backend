package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> createProducts(List<ProductDTO> productDTOs);
    ProductDTO getProductByName(String name);
}
