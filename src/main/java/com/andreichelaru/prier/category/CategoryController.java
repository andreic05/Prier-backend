package com.andreichelaru.prier.category;

import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import jakarta.validation.Valid;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        LOGGER.info("Retrieving categories");

        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        LOGGER.info("Retrieving category with id {}", id);

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponse>> searchCategory(@RequestParam String name, @RequestParam Long productId) {
        LOGGER.info("GET /search with name {} and product id {}", name, productId);
        if (name != null) return ResponseEntity.ok(List.of(categoryService.getCategoryByName(name)));
        if (productId != null) return ResponseEntity.ok(categoryService.getCategoriesByProductId(productId));

        throw new BusinessException(List.of(
                new ErrorModel("INVALID_SEARCH", "Query parameters required")));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        LOGGER.info("Creating category {}", categoryRequest);

        return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        LOGGER.info("Updating category {}", categoryRequest);

        return ResponseEntity.ok(categoryService.updateCategory(categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
        LOGGER.info("Deleting category with id {}", id);

        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }
}
