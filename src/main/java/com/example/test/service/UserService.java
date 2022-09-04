package com.example.test.service;

import com.example.test.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
}
