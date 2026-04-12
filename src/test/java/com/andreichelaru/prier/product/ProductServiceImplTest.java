package com.andreichelaru.prier.product;
import com.andreichelaru.prier.product.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private final ProductServiceImpl productService = new ProductServiceImpl();

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Product 2", result.get(1).getName());
    }
}