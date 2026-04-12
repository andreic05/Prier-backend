package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.response.ProductResponse;
import com.andreichelaru.prier.product.dto.request.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public ProductResponse getProductByName(@RequestParam(required = false) String name) {
        if (name != null) {
            return productService.getProductByName(name);
        }

        return null;
    }

    @PostMapping
    public com.andreichelaru.prier.product.dto.response.ProductResponse createProduct(@Valid @RequestBody ProductRequest ProductResponse) {
        return productService.createProduct(ProductResponse);
    }

    @PostMapping("/bulk")
    public List<ProductResponse> createProducts(@RequestBody List<ProductRequest> ProductResponses) {
        return productService.createProducts(ProductResponses);
    }

}
