package com.andreichelaru.prier.category;

import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<CategoryResponse>>  getCategories();
    ResponseEntity<CategoryResponse> getCategoryById(Long id);
    ResponseEntity<CategoryResponse> getCategoryByName(String name);
    ResponseEntity<List<CategoryResponse>> getCategoriesByProductId(Long productId);
    ResponseEntity<CategoryResponse> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<CategoryResponse> updateCategory(CategoryRequest categoryRequest);
    ResponseEntity<CategoryResponse> deleteCategoryById(Long id);
}
