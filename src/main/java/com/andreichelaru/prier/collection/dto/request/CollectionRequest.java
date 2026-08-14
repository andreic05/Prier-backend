package com.andreichelaru.prier.collection.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"id", "name"})
public class CollectionRequest {
    private Long id;
    private String name;
    private String description;
}
