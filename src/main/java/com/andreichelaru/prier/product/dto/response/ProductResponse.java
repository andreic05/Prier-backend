package com.andreichelaru.prier.product.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private List<String> categories;
    private List<String> collections;
    private String metadata;
    // images
    // variants
}

