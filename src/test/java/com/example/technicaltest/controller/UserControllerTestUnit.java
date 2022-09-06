package com.example.technicaltest.controller;

import com.example.technicaltest.exception.*;
import com.example.technicaltest.model.UserDTO;
import com.example.technicaltest.response.ResponseHandler;
import com.example.technicaltest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserControllerTestUnit {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Test
    public void whenCreatingUser_thenReturnRightUserCreatedWithStatus201() {
        UserDTO user = generateUser();
        ResponseEntity<Object> responseEntityExpected =
                ResponseHandler.createResponseEntity(
                        "User Created",
                        HttpStatus.CREATED,
                        user
                );
        given(service.createUser(any(UserDTO.class))).willReturn(user);

        assertThat(controller.registerUser(user)).isEqualTo(responseEntityExpected);
    }

    @Test
    public void whenCreatingUserWithInvalidName_thenReturnInvalidUsernameException() {
        String errorMessage = "You must put a name";
        UserDTO user = generateUser();
        given(service.createUser(any()))
                .willThrow(new InvalidUsernameException(errorMessage));

        assertThatExceptionOfType(InvalidUsernameException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessage);
    }

    @Test
    public void whenCreatingUserWithInvalidBirthdate_thenReturnInvalidBirthdateException() {
        String errorMessageNullBirthdate = "Birthdate cannot be null";
        String errorMessageYoungerThan18 = "You must be an adult in your country to register";
        UserDTO user = generateUser();
        given(service.createUser(any()))
                .willThrow(new InvalidBirthdateException(errorMessageNullBirthdate),
                        new InvalidBirthdateException(errorMessageYoungerThan18));
        assertThatExceptionOfType(InvalidBirthdateException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessageNullBirthdate);
        assertThatExceptionOfType(InvalidBirthdateException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessageYoungerThan18);
    }

    @Test
    public void whenCreatingUserWithNullCountry_thenReturnInvalidCountryException() {
        String errorMessageNullCountry = "Country residency cannot be null";
        String errorMessageNotFrench = "Only French residents can register";
        UserDTO user = generateUser();
        given(service.createUser(any()))
                .willThrow(new InvalidCountryException(errorMessageNullCountry),
                        new InvalidCountryException(errorMessageNotFrench));

        assertThatExceptionOfType(InvalidCountryException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessageNullCountry);
        assertThatExceptionOfType(InvalidCountryException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessageNotFrench);
    }

    @Test
    public void whenCreatingUserWithInvalidGender_thenReturnInvalidGenderException() {
        String errorMessage = "Gender must be female, male or other";
        UserDTO user = generateUser();
        given(service.createUser(any()))
                .willThrow(new InvalidGenderException(errorMessage));

        assertThatExceptionOfType(InvalidGenderException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessage);
    }

    @Test
    public void whenCreatingUserWithInvalidPhoneNumber_thenReturnInvalidPhoneNumberException() {
        String errorMessage = "Invalid phone number format";
        UserDTO user = generateUser();
        given(service.createUser(any()))
                .willThrow(new InvalidPhoneNumberException(errorMessage));

        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> controller.registerUser(user))
                .withMessage(errorMessage);
    }

    @Test
    public void whenFetchingUserById_thenReturnRightUserWithStatus200() {
        final Long id = 1L;
        UserDTO user = generateUser();

        ResponseEntity<Object> responseEntityExpected =
                ResponseHandler.createResponseEntity(
                        "",
                        HttpStatus.OK,
                        user
                );
        given(service.getUserById(id)).willReturn(user);

        assertThat(controller.fetchUserWithId(id)).isEqualTo(responseEntityExpected);
    }

    @Test
    public void whenFetchingUserByWrongId_thenReturnUserNotFoundException() {
        final Long id = 1L;
        String errorMessage = "Invalid phone number format";
        given(service.getUserById(id))
                .willThrow(new UserNotFoundException(errorMessage));

        assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> controller.fetchUserWithId(id))
                .withMessage(errorMessage);
    }

    private UserDTO generateUser() {
        UserDTO user = new UserDTO();
        user.setName("Tester1");
        user.setBirthdate(LocalDate.of(1999, Month.FEBRUARY, 1));
        user.setCountry("France");
        user.setGender("Male");
        user.setPhoneNumber("+33695285727");
        return user;
    }
}
