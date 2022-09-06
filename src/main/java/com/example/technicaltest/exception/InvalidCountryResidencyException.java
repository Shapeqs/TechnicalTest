package com.example.technicaltest.exception;

public class InvalidCountryResidencyException extends RuntimeException {

    /**
     * Exception for user creation service when country residency in invalid
     * @param errorMessage error message
     */
    public InvalidCountryResidencyException(String errorMessage) {
        super(errorMessage);
    }
}
