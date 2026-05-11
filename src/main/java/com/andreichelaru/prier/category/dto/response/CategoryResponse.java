package com.andreichelaru.prier.category.dto.response;

import com.andreichelaru.prier.product.dto.response.ProductResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString(of = {"id", "name"})
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<ProductResponse> products;
}
