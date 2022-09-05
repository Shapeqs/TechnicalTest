package com.example.test.exception;

public class InvalidUserNameException extends RuntimeException {
    public InvalidUserNameException(String errorMessage) {
        super(errorMessage);
    }
}
