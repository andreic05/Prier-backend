package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public ProductDTO getProductByName(@RequestParam(required = false) String name) {
        if (name != null) {
            return productService.getProductByName(name);
        }

        return null;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PostMapping("/bulk")
    public List<ProductDTO> createProducts(@RequestBody List<ProductDTO> productDTOs) {
        return productService.createProducts(productDTOs);
    }

}
