package com.example.test.exception;

import com.example.test.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            InvalidUserNameException.class,
            InvalidBirthdateException.class,
            InvalidCountryResidencyException.class,
            InvalidPhoneNumberException.class,
            InvalidGenderException.class
    })
    protected ResponseEntity<Object> handleCreateUserExceptions(RuntimeException exception) {
        return ResponseHandler.createResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleGetUserExceptions(RuntimeException exception) {
        return ResponseHandler.createResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
