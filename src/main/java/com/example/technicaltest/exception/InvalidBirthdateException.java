package com.example.technicaltest.exception;

public class InvalidBirthdateException extends RuntimeException {

    /**
     * Exception for user creation service when birthdate in invalid
     * @param errorMessage error message
     */
    public InvalidBirthdateException(String errorMessage) {
        super(errorMessage);
    }
}
