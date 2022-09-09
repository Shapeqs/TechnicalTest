package com.example.technicaltest.response;

import com.example.technicaltest.model.UserDTO;
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
     * @return an ResponseEntity Object
     */
    public static ResponseEntity<Object> createErrorResponseEntity(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
