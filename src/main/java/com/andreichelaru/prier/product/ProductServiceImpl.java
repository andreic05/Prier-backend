package com.andreichelaru.prier.product;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.common.exceptions.ResourceNotFoundException;
import com.andreichelaru.prier.product.dto.request.ProductRequest;
import com.andreichelaru.prier.product.dto.response.ProductResponse;
import com.andreichelaru.prier.product.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        LOGGER.debug("Fetching all products");
        List<Product> products = (ArrayList<Product>) productRepository.findAll();

        LOGGER.debug("Products fetched: {}", products);
        LOGGER.debug("Converting to dto");
        return productMapper.toProductResponseList(products);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        LOGGER.debug("Fetching product by id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        LOGGER.debug("Product fetched: {}", product);
        LOGGER.debug("Converting to dto");
        if (product.isPresent()) return productMapper.toResponse(product.get());

        throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_ID", "No product with id " + id)));
    }

    @Override
    public ProductResponse getProductByName(String name) {
        LOGGER.debug("Fetching product by name: {}", name);
        Optional<Product> product = productRepository.getProductByName(name);
        LOGGER.debug("Product fetched: {}", product);
        LOGGER.debug("Converting to dto");
        if (product.isPresent()) return productMapper.toResponse(product.get());

        throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_NAME", "No product with name " + name)));
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        LOGGER.debug("Name unique check: {}", productRequest.getName());
        if (productRepository.existsByName(productRequest.getName())) throw new BusinessException(List.of(
                new ErrorModel("PRODUCT_ALREADY_EXISTS", "Product with name " + productRequest.getName())));

        LOGGER.debug("Creating product: {}", productRequest);
        Product product = productMapper.toProduct(productRequest);
        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> createProducts(List<ProductRequest> productRequests) {
        List<Product> products = new ArrayList<>();
        LOGGER.debug("Name exists and unique check: {}", productRequests.size());
        for (ProductRequest productRequest : productRequests) {
            if (productRepository.existsByName(productRequest.getName())) throw new BusinessException(List.of(
                    new ErrorModel("PRODUCT_ALREADY_EXISTS", "Product with name " + productRequest.getName())));
            if (productRequest.getName() == null) throw new BusinessException(List.of(
                    new ErrorModel("INVALID_NAME", "Product name is required")));

            products.add(productMapper.toProduct(productRequest));
        }
        LOGGER.debug("Creating products: {}", products);
        products = (ArrayList<Product>) productRepository.saveAll(products);

        return productMapper.toProductResponseList(products);
    }
}
