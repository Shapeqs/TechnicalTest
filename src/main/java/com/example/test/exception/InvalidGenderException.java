package com.example.test.exception;

public class InvalidGenderException extends RuntimeException {

    /**
     * Exception for user creation service when gender in invalid
     * @param errorMessage error message
     */
    public InvalidGenderException(String errorMessage) {
        super(errorMessage);
    }
}
