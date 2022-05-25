package com.example.bruce.model.exception;

import com.example.bruce.model.response.ResponseException;

public class ResponseObject {
    private String message;
    private int status;

    public ResponseObject(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static ResponseObject build(ResponseException ex) {
        return new ResponseObject(ex.getMessage(), ex.getStatus());
    }
}
