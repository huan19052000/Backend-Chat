package com.example.bruce.model.response;

import org.springframework.http.HttpStatus;

public class ResponseException extends Exception {
    private HttpStatus httpCode;
    private int status;

    public ResponseException(String message) {
        super(message);
        httpCode = HttpStatus.BAD_REQUEST;
        status = 1;
    }

    public ResponseException(String message, HttpStatus httpCode) {
        super(message);
        this.httpCode = httpCode;
        status = 1;
    }

    public ResponseException(String message, HttpStatus httpCode, int status) {
        super(message);
        this.httpCode = httpCode;
        this.status = status;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public int getStatus() {
        return status;
    }
}
