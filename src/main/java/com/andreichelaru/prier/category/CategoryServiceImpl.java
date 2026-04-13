package com.andreichelaru.prier.category;

import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> getCategories() {
        return List.of();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        LOGGER.info("Fetching category by id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            LOGGER.info("Category found: {}", category.get());
            return null;
        }

        throw new BusinessException(List.of(new ErrorModel("INVALID_ID", "Category not found")));
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<CategoryResponse> getCategoriesByProductId(Long productId) {
        return List.of();
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse deleteCategoryById(Long id) {
        return null;
    }
}
