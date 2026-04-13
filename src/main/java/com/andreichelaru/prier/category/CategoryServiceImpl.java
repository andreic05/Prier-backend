package com.andreichelaru.prier.category;

import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import com.andreichelaru.prier.category.mapper.CategoryMapper;
import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.common.exceptions.ResourceNotFoundException;
import com.andreichelaru.prier.product.Product;
import com.andreichelaru.prier.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        LOGGER.debug("Retrieving categories");
        List<Category> categories =  categoryRepository.findAll();

        return ResponseEntity.ok(categoryMapper.toCategoryResponseList(categories));
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(Long id) {
        LOGGER.debug("Retrieving category by id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            LOGGER.debug("Category found: {}", category.get());
            return ResponseEntity.ok(categoryMapper.toCategoryResponse(category.get()));
        }

        throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_ID", "Category not found")));
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryByName(String name) {
        LOGGER.debug("Retrieving category by name: {}", name);
        Optional<Category> category = categoryRepository.getCategoryByName(name);
        if (category.isPresent()) {
            LOGGER.debug("Category found: {}", category.get());
            return ResponseEntity.ok(categoryMapper.toCategoryResponse(category.get()));
        }

        throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_NAME", "Category not found")));
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getCategoriesByProductId(Long productId) {
        LOGGER.debug("Retrieving product by id: {}", productId);
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new ResourceNotFoundException(List.of(new ErrorModel("INVALID_ID", "Product not found")));

        LOGGER.debug("Product found: {}", product.get());
        return ResponseEntity.ok(categoryMapper.toCategoryResponseList(new ArrayList<>(product.get().getCategories())));
    }

    @Override
    public ResponseEntity<CategoryResponse> addCategory(CategoryRequest categoryRequest) {
        LOGGER.debug("Adding category: {}", categoryRequest);
        if  (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new BusinessException(List.of(new ErrorModel("NAME_EXISTS", "Category already exists")));
        }

        LOGGER.debug("Category added");
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(categoryRepository.save(categoryMapper.toCategory(categoryRequest))));
    }

    @Override
    public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest categoryRequest) {
        LOGGER.debug("Updating category: {}", categoryRequest);
        LOGGER.debug("Retrieving category by id: {}", categoryRequest.getId());
        Optional<Category> category = categoryRepository.findById(categoryRequest.getId());
        if (category.isEmpty())
            throw new BusinessException(List.of(new ErrorModel("INVALID_ID", "Category doesn't exist")));

        Category categoryEntity = category.get();
        if (categoryRepository.existsByName(categoryRequest.getName()))
            throw new BusinessException(List.of(new ErrorModel("NAME_EXISTS", "Category already exists")));
        categoryEntity.setName(categoryRequest.getName());
        categoryEntity.setDescription(categoryRequest.getDescription());

        LOGGER.debug("Category updated");
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(categoryRepository.save(categoryEntity)));
    }

    @Override
    public ResponseEntity<CategoryResponse> deleteCategoryById(Long id) {
        LOGGER.debug("Deleting category by id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            throw new BusinessException(List.of(new ErrorModel("INVALID_ID", "Category doesn't exist")));
        categoryRepository.deleteById(id);

        return ResponseEntity.ok(categoryMapper.toCategoryResponse(category.get()));
    }
}
