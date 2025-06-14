package com.hotel.controller;

import com.hotel.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorResponse.of(ex.getMessage(), status.value()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> aggregatedException(Exception ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(ErrorResponse.of(ex.getMessage(), status.value()), status);
    }
}
