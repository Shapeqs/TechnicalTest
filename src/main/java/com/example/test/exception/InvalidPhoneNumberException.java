package com.example.test.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
