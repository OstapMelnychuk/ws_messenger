package com.example.ws_messenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyCreatedException extends RuntimeException {
    public AlreadyCreatedException(String message) {
        super(message);
    }
}
