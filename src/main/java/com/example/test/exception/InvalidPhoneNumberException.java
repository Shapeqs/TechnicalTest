package com.example.test.exception;

public class InvalidPhoneNumberException extends RuntimeException {

    /**
     * Exception for user creation service when phone number in invalid
     * @param errorMessage error message
     */
    public InvalidPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
