package com.example.test.exception;

public class InvalidUsernameException extends RuntimeException {

    /**
     * Exception for user creation service when username in invalid
     * @param errorMessage error message
     */
    public InvalidUsernameException(String errorMessage) {
        super(errorMessage);
    }
}
