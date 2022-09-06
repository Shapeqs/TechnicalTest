package com.example.technicaltest.exception;

public class UserNotFoundException extends RuntimeException {

    /**
     * Exception for user fetching service
     * @param errorMessage error message
     */
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
