package com.example.technicaltest.service;

import com.example.technicaltest.model.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
}
