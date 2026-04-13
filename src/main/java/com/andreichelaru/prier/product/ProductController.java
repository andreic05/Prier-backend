package com.andreichelaru.prier.product;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.product.dto.response.ProductResponse;
import com.andreichelaru.prier.product.dto.request.ProductRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        LOGGER.info("Getting all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        LOGGER.info("Getting product with id: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public ProductResponse getProductByName(@RequestParam(required = false) String name) {
        LOGGER.info("GET /search called with name: {}", name);
        if (name != null) {
            LOGGER.info("Getting product by name: {}", name);
            return productService.getProductByName(name);
        }

        throw new BusinessException(List.of(new ErrorModel("MISSING_QUERY_PARAMETER", "Product name is required")));
    }

    @PostMapping
    public com.andreichelaru.prier.product.dto.response.ProductResponse createProduct(@Valid @RequestBody ProductRequest ProductResponse) {
        LOGGER.info("Creating product with name: {}", ProductResponse.getName());
        return productService.createProduct(ProductResponse);
    }

    @PostMapping("/bulk")
    public List<ProductResponse> createProducts(@RequestBody List<ProductRequest> ProductResponses) {
        LOGGER.info("Creating products with {} products", ProductResponses.size());
        return productService.createProducts(ProductResponses);
    }

}
