package com.example.test.controller;

import com.example.test.model.UserDTO;
import com.example.test.response.ResponseHandler;
import com.example.test.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping(path = "")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO user) {
        UserDTO userCreated = this.userService.createUser(user);
        return ResponseHandler.createResponseEntity("User Created", HttpStatus.CREATED, userCreated);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> fetchUserWithId(@PathVariable Long id) {
        UserDTO userFetched = this.userService.getUserById(id);
        return ResponseHandler.createResponseEntity("", HttpStatus.OK, userFetched);
    }
}
