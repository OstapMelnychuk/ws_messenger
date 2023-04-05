package com.example.ws_messenger.exception.handler;

import com.example.ws_messenger.exception.AlreadyCreatedException;
import com.example.ws_messenger.exception.BadDataFormatException;
import com.example.ws_messenger.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GeneralExceptionHandler {
    @ExceptionHandler({BadDataFormatException.class, AlreadyCreatedException.class})
    public ResponseEntity<String> handleBadRequestErrors(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundErrors(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
