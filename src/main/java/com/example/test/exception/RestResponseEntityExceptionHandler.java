package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidUserNameException.class, InvalidBirthdateException.class,
            InvalidCountryResidencyException.class, InvalidPhoneNumberException.class, InvalidGenderException.class})
    protected ResponseEntity<Object> handleCreateUserExceptions(RuntimeException exception) {
        return getResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    protected ResponseEntity<Object> handleGetUserExceptions(RuntimeException exception) {
        return getResponseEntity(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    private ResponseEntity<Object> getResponseEntity(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
