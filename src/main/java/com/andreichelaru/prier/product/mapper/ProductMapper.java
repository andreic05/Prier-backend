package com.andreichelaru.prier.product.mapper;

import com.andreichelaru.prier.product.Product;
import com.andreichelaru.prier.product.dto.request.ProductRequest;
import com.andreichelaru.prier.product.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        product.setMetaData(product.getMetaData());

        return productResponse;
    }

    public Product toProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setMetaData(product.getMetaData());

        return product;
    }

    public List<Product> toProductList(List<ProductRequest> productRequests) {
        List<Product> products = new ArrayList<>();
        for (ProductRequest productRequest : productRequests) {
            products.add(toProduct(productRequest));
        }

        return products;
    }

    public List<ProductResponse> toProductResponseList(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(toResponse(product));
        }

        return productResponses;
    }
}
