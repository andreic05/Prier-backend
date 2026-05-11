package com.andreichelaru.prier.category.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = "description")
public class CategoryRequest {
    private Long id;

    @NotNull
    private String name;

    private String description;


}
