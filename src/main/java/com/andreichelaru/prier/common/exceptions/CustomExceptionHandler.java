package com.andreichelaru.prier.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException ex) {
        LOGGER.warn("BusinessException occurred with {} errors", ex.getErrors().size());
        LOGGER.error(ex.getErrors().toString());

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

        LOGGER.warn("MethodArgumentNotValidException occurred with {} errors", errors.size());
        LOGGER.error(errors.toString());

        return ResponseEntity.badRequest().body(errors);
    }
}

