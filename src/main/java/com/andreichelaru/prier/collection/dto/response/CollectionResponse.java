package com.andreichelaru.prier.collection.dto.response;

import com.andreichelaru.prier.product.dto.response.ProductResponse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(of = {"id", "name"})
public class CollectionResponse {

    private Long id;

    @NotEmpty
    private String name;

    private String description;
    private String metadata;
    private List<ProductResponse> products;
}
