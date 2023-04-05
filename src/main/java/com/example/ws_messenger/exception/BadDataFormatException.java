package com.example.ws_messenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadDataFormatException extends RuntimeException {

    public BadDataFormatException(String message) {
        super(message);
    }
}
