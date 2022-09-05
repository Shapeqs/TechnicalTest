package com.example.test.exception;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException(String errorMessage) {
        super(errorMessage);
    }
}
