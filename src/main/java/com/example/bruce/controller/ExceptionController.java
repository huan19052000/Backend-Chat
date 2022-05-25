package com.example.bruce.controller;

import com.example.bruce.model.exception.ResponseObject;
import com.example.bruce.model.response.ResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Object> handlerResponseException(ResponseException ex) {
        return new ResponseEntity<>(ResponseObject.build(ex), ex.getHttpCode());
    }
}
