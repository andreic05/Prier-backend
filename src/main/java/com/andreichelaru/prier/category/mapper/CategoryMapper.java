package com.andreichelaru.prier.category.mapper;

import com.andreichelaru.prier.category.Category;
import com.andreichelaru.prier.category.dto.request.CategoryRequest;
import com.andreichelaru.prier.category.dto.response.CategoryResponse;
import com.andreichelaru.prier.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    private final ProductMapper productMapper;

    @Autowired
    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setProducts(productMapper.toProductResponseList(new ArrayList<>(category.getProducts())));

        return categoryResponse;
    }

    public Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setId(categoryRequest.getId());
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        return category;
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categoryList) {

        return categoryList.stream().map(this::toCategoryResponse).toList();
    }

    public List<Category> toCategoryList(List<CategoryRequest> categoryRequestList) {

        return categoryRequestList.stream().map(this::toCategory).toList();
    }
}
