package com.andreichelaru.prier.common.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorModel {

    private String code;
    private String message;

    public ErrorModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static List<ErrorModel> createList(ErrorModel... errorModels) {

        return new ArrayList<>(Arrays.asList(errorModels));
    }
}
