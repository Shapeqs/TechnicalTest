package com.example.technicaltest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to create ResponseEntity
 * @author Clement Querre
 */
public class ResponseHandler {

    /**
     * Create a ResponseEntity Object used in the API responses
     * @param message the message of the response
     * @param status the status of the response
     * @param data the data of the object when needed
     * @return an ResponseEntity Object
     */
    public static ResponseEntity<Object> createResponseEntity(String message, HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        map.put("data", data);
        return new ResponseEntity<>(map, status);
    }
}
