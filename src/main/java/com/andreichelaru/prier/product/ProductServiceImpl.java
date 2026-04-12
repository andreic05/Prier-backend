package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.request.ProductRequest;
import com.andreichelaru.prier.product.dto.response.ProductResponse;
import com.andreichelaru.prier.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = (ArrayList<Product>) productRepository.findAll();
        List<ProductResponse> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productDTO = productMapper.toResponse(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toResponse).orElse(null);
    }

    @Override
    public ProductResponse getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);

        return product.map(productMapper::toResponse).orElse(null);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> createProducts(List<ProductRequest> productRequests) {
        List<Product> products = new ArrayList<>();
        for (ProductRequest productDTO : productRequests) {
            products.add(productMapper.toProduct(productDTO));
        }
        products = (ArrayList<Product>) productRepository.saveAll(products);

        List<ProductResponse> newProductResponses = new ArrayList<>();
        for (Product product : products) {
            newProductResponses.add(productMapper.toResponse(product));
        }

        return newProductResponses;
    }
}
