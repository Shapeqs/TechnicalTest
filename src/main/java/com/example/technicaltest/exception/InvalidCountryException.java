package com.example.technicaltest.exception;

public class InvalidCountryException extends RuntimeException {

    /**
     * Exception for user creation service when country residency in invalid
     * @param errorMessage error message
     */
    public InvalidCountryException(String errorMessage) {
        super(errorMessage);
    }
}
