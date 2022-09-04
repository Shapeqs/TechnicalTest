package com.example.test.service;

import com.example.test.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    public List<User> getUsers() {
        return List.of(
                new User(1L, "Jean", LocalDate.of(1999,5,1), "France", "oui", "oui"),
                new User(1L, "Jean", LocalDate.of(1999,5,1), "France", "oui", "oui")
        );
    }
}
