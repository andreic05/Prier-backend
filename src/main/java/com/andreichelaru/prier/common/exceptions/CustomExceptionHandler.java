package com.andreichelaru.prier.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException ex) {

        return ResponseEntity.badRequest().body(ex.getErrors());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorModel> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((er) -> {
            ErrorModel error = new ErrorModel();
            error.setCode(er.getCode());
            error.setMessage(er.getDefaultMessage());
            errors.add(error);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}

