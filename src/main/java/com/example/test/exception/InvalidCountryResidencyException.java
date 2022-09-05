package com.example.test.exception;

public class InvalidCountryResidencyException extends RuntimeException {
    public InvalidCountryResidencyException(String errorMessage) {
        super(errorMessage);
    }
}
