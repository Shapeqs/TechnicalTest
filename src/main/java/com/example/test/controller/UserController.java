package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.model.UserDTO;
import com.example.test.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping(path = "")
    public UserDTO registerUser(@RequestBody UserDTO user) {
        return this.userService.createUser(user);
    }

    @GetMapping(path = "/{id}")
    public UserDTO fetchUserWithId(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }
}