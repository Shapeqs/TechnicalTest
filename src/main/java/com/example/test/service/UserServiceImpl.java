package com.example.test.service;

import com.example.test.entity.User;
import com.example.test.exception.*;
import com.example.test.model.UserDTO;
import com.example.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.capitalize;

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
            throw new UserNotFoundException("User do not exist");
        }
        return mapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        checkData(userDTO);
        formatUserData(userDTO);
        User user = mapper.map(userDTO, User.class);
        return mapper.map(repository.save(user), UserDTO.class);
    }

    private void formatUserData(UserDTO user) {
        user.setName(formatUserName(user.getName().toLowerCase()));
        user.setCountryResidency(capitalize(user.getCountryResidency().toLowerCase()));
        user.setGender(capitalize(user.getGender().toLowerCase()));
    }

    private void checkData(UserDTO userDTO) {
        checkName(userDTO.getName());
        checkBirthdate(userDTO.getBirthdate());
        checkCountryResidency(userDTO.getCountryResidency());
        checkGender(userDTO.getGender());
        checkPhoneNumber(userDTO.getPhoneNumber());
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidUserNameException("You must put a name");
        }
    }

    private void checkBirthdate(LocalDate birthdate) {
        LocalDate curDate = LocalDate.now();
        if (birthdate == null) {
            throw new InvalidBirthdateException("Birthdate cannot be null");
        }
        if (Period.between(birthdate, curDate).getYears() < 18) {
            throw new InvalidBirthdateException("You must be an adult to register");
        }
    }

    private void checkCountryResidency(String countryResidency) {
        if( countryResidency == null) {
            throw new InvalidCountryResidencyException("Country residency cannot be null");
        }
        if (countryResidency.compareToIgnoreCase("France") != 0) {
            throw new InvalidCountryResidencyException("Only French residents can register");
        }
    }

    private void checkGender(String gender) {
        String female = "female";
        String male = "male";
        String other = "other";
        if (gender.compareToIgnoreCase(female) != 0
        && gender.compareToIgnoreCase(male) != 0
        && gender.compareToIgnoreCase(other) != 0) {
            throw new InvalidGenderException("Gender must be female, male or other");
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        String regex = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
        Pattern pattern = Pattern.compile(regex);
        if (!phoneNumber.isBlank()) {
            Matcher matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                throw new InvalidPhoneNumberException("Invalid phone number format");
            }
        }
    }

    private String formatUserName(String name) {
        StringBuilder formated = new StringBuilder();
        String[] names = null;
        String sep;
        if (name.contains("-")) {
            names = name.split("-");
            sep = "-";
        } else if (name.contains(" ")) {
            names = name.split(" ");
            sep = " ";
        } else {
            return capitalize(name);
        }
        formated.append(capitalize(names[0]));
        for (int i = 1; i < names.length; i++) {
            formated.append(sep);
            formated.append(capitalize(names[i]));
        }
        return formated.toString();
    }
}
