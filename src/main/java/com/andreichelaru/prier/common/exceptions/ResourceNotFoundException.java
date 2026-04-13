package com.andreichelaru.prier.common.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private List<ErrorModel> errors;

    public ResourceNotFoundException(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
