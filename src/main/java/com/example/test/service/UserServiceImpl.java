package com.example.test.service;

import com.example.test.entity.User;
import com.example.test.model.UserDTO;
import com.example.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository repository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return mapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // TODO : Check data
        User user = mapper.map(userDTO, User.class);
        return mapper.map(repository.save(user), UserDTO.class);
    }
}
