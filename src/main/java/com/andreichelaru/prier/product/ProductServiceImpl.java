package com.andreichelaru.prier.product;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
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
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productDTO = productMapper.toResponse(product);
            productResponses.add(productDTO);
        }

        return productResponses;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) return productMapper.toResponse(product.get());

        throw new BusinessException(ErrorModel.createList(new ErrorModel("INVALID_ID", "No product with id " + id)));
    }

    @Override
    public ProductResponse getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);

        return product.map(productMapper::toResponse).orElse(null);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) throw new BusinessException(List.of(
                new ErrorModel("PRODUCT_ALREADY_EXISTS", "Product with name " + productRequest.getName())));

        Product product = productMapper.toProduct(productRequest);
        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> createProducts(List<ProductRequest> productRequests) {
        List<Product> products = new ArrayList<>();
        for (ProductRequest productRequest : productRequests) {
            if (productRepository.existsByName(productRequest.getName())) throw new BusinessException(List.of(
                    new ErrorModel("PRODUCT_ALREADY_EXISTS", "Product with name " + productRequest.getName())));
            if (productRequest.getName() == null) throw new BusinessException(List.of(
                    new ErrorModel("INVALID_NAME", "Product name is required")));

            products.add(productMapper.toProduct(productRequest));
        }
        products = (ArrayList<Product>) productRepository.saveAll(products);

        return productMapper.toProductResponseList(products);
    }
}
