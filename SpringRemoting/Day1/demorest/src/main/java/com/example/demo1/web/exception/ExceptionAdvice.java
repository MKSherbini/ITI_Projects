package com.example.demo1.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    //    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleException(ResourceNotFoundException e) {
        log.warn("ResourceNotFoundException happened");
        var error = new ErrorDetails();
        error.setErrorCode(HttpStatus.NOT_FOUND.toString());
        error.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
