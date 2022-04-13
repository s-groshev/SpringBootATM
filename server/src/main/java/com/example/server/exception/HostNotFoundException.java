package com.example.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException() {
    }
    public HostNotFoundException(String message) {
        super(message);
    }
}