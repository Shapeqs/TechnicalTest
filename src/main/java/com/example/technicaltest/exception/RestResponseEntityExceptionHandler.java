package com.example.technicaltest.exception;

import com.example.technicaltest.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Adviser to handle all exceptions to the application
 * @author Clement Querre
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Catch exception for creation user request
     * @param exception the exception thrown
     * @return an ResponseEntity with the correct Bad Request status and an exception message
     */
    @ExceptionHandler(value = {
            InvalidUsernameException.class,
            InvalidBirthdateException.class,
            InvalidCountryException.class,
            InvalidPhoneNumberException.class,
            InvalidGenderException.class
    })
    protected ResponseEntity<Object> handleCreateUserExceptions(RuntimeException exception) {
        return ResponseHandler.createResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    /**
     * Catch exception for creation request
     * @param exception the exception thrown
     * @return an ResponseEntity with the correct Not Found status and an exception message
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleGetUserExceptions(RuntimeException exception) {
        return ResponseHandler.createResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
