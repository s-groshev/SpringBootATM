package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ATMInternalErrorException extends RuntimeException{

    public ATMInternalErrorException() {
    }

    public ATMInternalErrorException(String message) {
        super(message);
    }
}
