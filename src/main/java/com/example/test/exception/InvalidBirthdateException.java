package com.example.test.exception;

public class InvalidBirthdateException extends RuntimeException {
    public InvalidBirthdateException(String errorMessage) {
        super(errorMessage);
    }
}
