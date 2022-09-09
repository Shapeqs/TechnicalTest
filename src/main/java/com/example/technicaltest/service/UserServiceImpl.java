package com.example.technicaltest.service;

import com.example.technicaltest.entity.Country;
import com.example.technicaltest.entity.Gender;
import com.example.technicaltest.entity.User;
import com.example.technicaltest.exception.*;
import com.example.technicaltest.model.UserDTO;
import com.example.technicaltest.repository.CountryDAO;
import com.example.technicaltest.repository.GenderDAO;
import com.example.technicaltest.repository.UserDAO;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final CountryDAO countryDAO;

    private final GenderDAO genderDAO;

    private final ModelMapper mapper;

    public UserServiceImpl(
            UserDAO userDAO,
            CountryDAO countryDAO,
            GenderDAO genderDAO,
            ModelMapper mapper
    ) {
        this.userDAO = userDAO;
        this.countryDAO = countryDAO;
        this.genderDAO = genderDAO;
        this.mapper = mapper;
    }

    /**
     * Get user by its id
     *
     * @param id the user id
     * @return a user Object if exist in database
     * @throws UserNotFoundException if user not exist
     */
    @Override
    public UserDTO getUserById(Long id) {
        return mapper.map(
                userDAO.findById(id).orElseThrow(
                        () -> new UserNotFoundException("User do not exist")
                ), UserDTO.class);
    }

    /**
     * Create user
     *
     * @param userDTO Container for all user data
     * @return Container of all data from the created user
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        checkData(user);
        return mapper.map(userDAO.save(user), UserDTO.class);
    }

    /***
     * Simple method to check every data from the container is right
     * @param user Container of all data from user
     */
    private void checkData(User user) {
        checkName(user.getName());
        user.setCountry(checkCountryResidency(user.getCountry()));
        checkBirthdate(user.getBirthdate(), user.getCountry());
        user.setGender(checkGender(user.getGender()));
        checkPhoneNumber(user.getPhoneNumber());
    }

    /**
     * Check if username is not blank
     *
     * @param name the name of the user
     * @throws InvalidUsernameException if name is blank
     */
    private void checkName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidUsernameException("You must put a name");
        }
    }

    /**
     * Check if birthdate is not null or user is younger than legal age
     *
     * @param birthdate birthdate of the user
     * @throws InvalidBirthdateException if birthdate is invalid
     */
    private void checkBirthdate(LocalDate birthdate, Country country) {
        LocalDate curDate = LocalDate.now();
        if (birthdate == null) {
            throw new InvalidBirthdateException("Birthdate cannot be null");
        }
        if (Period.between(birthdate, curDate).getYears() < country.getAgeOfMajority()) {
            throw new InvalidBirthdateException("You must be an adult in your country to register");
        }
    }

    /**
     * Check if country exist in database
     *
     * @param countryResidency the country residency of the user
     * @throws InvalidCountryException if country isn't valid
     */
    private Country checkCountryResidency(Country countryResidency) {
        if (countryResidency == null || StringUtils.isBlank(countryResidency.getName())) {
            throw new InvalidCountryException("Country residency cannot be null");
        }
        Country existInDatabase = this.countryDAO.findByName(countryResidency.getName());
        if (existInDatabase == null) {
            throw new InvalidCountryException("Only French residents can register");
        }
        return existInDatabase;
    }

    /**
     * Check if gender exist in database
     *
     * @param gender the gender of the user
     * @throws InvalidGenderException if the gender isn't valid
     */
    private Gender checkGender(Gender gender) {
        if (gender == null || StringUtils.isBlank(gender.getName())) {
            return null;
        }
        Gender existInDatabase = this.genderDAO.findByName(gender.getName());
        if (existInDatabase == null) {
            throw new InvalidGenderException("Gender must be female, male or other");
        }
        return existInDatabase;
    }

    /**
     * Check if phone number is a valid format
     *
     * @param phoneNumber the phone number of the user
     * @throws InvalidPhoneNumberException if phone number isn't valid
     */
    private void checkPhoneNumber(String phoneNumber) {
        String regex = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";
        Pattern pattern = Pattern.compile(regex);
        if (!StringUtils.isBlank(phoneNumber)) {
            Matcher matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                throw new InvalidPhoneNumberException("Invalid phone number format");
            }
        }
    }
}
