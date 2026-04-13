package com.andreichelaru.prier.category;

import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        LOGGER.info("Retrieving categories");

        return null;
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        LOGGER.info("Retrieving category with id {}", id);

        return null;
    }

    @GetMapping("/search")
    public List<CategoryResponse> searchCategory(@RequestParam String name, @RequestParam Long productId) {
        LOGGER.info("GET /search with name {} and product id {}", name, productId);
        if (name != null) return null;
        if (productId != null) return null;

        return null;
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        LOGGER.info("Creating category {}", categoryRequest);

        return null;
    }

    @PutMapping
    public CategoryResponse updateCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        LOGGER.info("Updating category {}", categoryRequest);

        return null;
    }

    @DeleteMapping("/{id}")
    public CategoryResponse deleteCategory(@PathVariable Long id) {
        LOGGER.info("Deleting category with id {}", id);

        return null;
    }
}
