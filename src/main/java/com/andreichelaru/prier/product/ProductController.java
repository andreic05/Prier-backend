package com.andreichelaru.prier.product;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.product.dto.response.ProductResponse;
import com.andreichelaru.prier.product.dto.request.ProductRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductResponse>> getProducts() {
        LOGGER.info("Getting all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        LOGGER.info("Getting product with id: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponse> getProductByName(@RequestParam(required = false) String name) {
        LOGGER.info("GET /search called with name: {}", name);
        if (name != null) {
            LOGGER.info("Getting product by name: {}", name);
            return ResponseEntity.ok(productService.getProductByName(name));
        }

        throw new BusinessException(List.of(new ErrorModel("MISSING_QUERY_PARAMETER", "Product name is required")));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest ProductResponse) {
        LOGGER.info("Creating product with name: {}", ProductResponse.getName());
        return ResponseEntity.ok(productService.createProduct(ProductResponse));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductResponse>> createProducts(@RequestBody List<ProductRequest> ProductResponses) {
        LOGGER.info("Creating products with {} products", ProductResponses.size());
        return ResponseEntity.ok(productService.createProducts(ProductResponses));
    }

}
