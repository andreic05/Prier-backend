package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.request.ProductRequest;
import com.andreichelaru.prier.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest productDTO);
    List<ProductResponse> createProducts(List<ProductRequest> productDTOs);
    ProductResponse getProductByName(String name);
}
