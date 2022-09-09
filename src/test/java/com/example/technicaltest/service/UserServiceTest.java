package com.example.technicaltest.service;

import com.example.technicaltest.entity.Country;
import com.example.technicaltest.entity.Gender;
import com.example.technicaltest.entity.User;
import com.example.technicaltest.exception.*;
import com.example.technicaltest.model.UserDTO;
import com.example.technicaltest.repository.CountryDAO;
import com.example.technicaltest.repository.GenderDAO;
import com.example.technicaltest.repository.UserDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private GenderDAO genderDAO;

    @Mock
    private CountryDAO countryDAO;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    UserServiceImpl service;

    @Test
    public void whenfetchingValidUserId_thenUserIsReturned() {
        User user = generateUser();
        given(userDAO.findById(any())).willReturn(Optional.of(user));

        final Optional<UserDTO> fetched = Optional.ofNullable(service.getUserById(any()));
        assertThat(fetched).isNotNull();
    }

    @Test
    public void whenfetchingWrongUserId_thenUserNotFoundExceptionThrown() {
        Long id = 1L;
        given(userDAO.findById(any())).willReturn(Optional.empty());
        assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> service.getUserById(id))
                .withMessage("User do not exist");
    }

    @Test
    public void whenCreatingUser_thenUserIsReturned() {
        UserDTO userDTO = generateDTO();

        User user = generateUser();

        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        given(this.mapper.map(user, UserDTO.class)).willReturn(userDTO);
        given(this.userDAO.save(any())).willReturn(user);
        given(this.countryDAO.findByName("France")).willReturn(generateCountry());
        given(this.genderDAO.findByName("Male")).willReturn(generateGender());

        UserDTO created = this.service.createUser(userDTO);
        assertThat(created.getName()).isEqualTo(userDTO.getName());
        assertThat(created.getBirthdate()).isEqualTo(userDTO.getBirthdate());
        assertThat(created.getCountry()).isEqualTo(userDTO.getCountry());
        assertThat(created.getGender()).isEqualTo(userDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(userDTO.getPhoneNumber());
    }

    @Test
    public void whenCreatingUserWithInvalidName_thenInvalidNameExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setName(" ");
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        assertThatExceptionOfType(InvalidUsernameException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("You must put a name");
    }

    @Test
    public void whenCreatingUserWithNullBirthdate_thenInvalidBirthdateExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setBirthdate(null);
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        given(this.countryDAO.findByName("France")).willReturn(generateCountry());
        assertThatExceptionOfType(InvalidBirthdateException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("Birthdate cannot be null");
    }

    @Test
    public void whenCreatingUserWithInvalidBirthdate_thenInvalidBirthdateExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setBirthdate(LocalDate.now());
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        given(this.countryDAO.findByName("France")).willReturn(generateCountry());
        assertThatExceptionOfType(InvalidBirthdateException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("You must be an adult in your country to register");
    }

    @Test
    public void whenCreatingUserWithNullCountry_thenInvalidCountryExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setCountry(null);
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        assertThatExceptionOfType(InvalidCountryException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("Country residency cannot be null");
    }

    @Test
    public void whenCreatingUserWithInvalidCountry_thenInvalidCountryExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setCountry(new Country("Italy", 18));
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        assertThatExceptionOfType(InvalidCountryException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("Only French residents can register");
    }

    @Test
    public void whenCreatingUserWithInvalidGender_thenInvalidGenderExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setGender(new Gender("Nope"));
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        given(this.countryDAO.findByName("France")).willReturn(generateCountry());
        assertThatExceptionOfType(InvalidGenderException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("Gender must be female, male or other");
    }

    @Test
    public void whenCreatingUserWithInvalidPhone_thenInvalidPhoneExceptionThrown() {
        UserDTO userDTO = generateDTO();
        User user = generateUser();
        user.setPhoneNumber("546546464985498");
        given(this.mapper.map(userDTO, User.class)).willReturn(user);
        given(this.countryDAO.findByName("France")).willReturn(generateCountry());
        given(this.genderDAO.findByName("Male")).willReturn(generateGender());
        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> this.service.createUser(userDTO))
                .withMessage("Invalid phone number format");
    }

    public UserDTO generateDTO() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("Tester1");
        user.setBirthdate(LocalDate.of(1999, Month.FEBRUARY, 1));
        user.setCountry(generateCountry().getName());
        user.setGender(generateGender().getName());
        user.setPhoneNumber("+33695285727");
        return user;
    }

    public User generateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Tester1");
        user.setBirthdate(LocalDate.of(1999, Month.FEBRUARY, 1));
        user.setCountry(generateCountry());
        user.setGender(generateGender());
        user.setPhoneNumber("+33695285727");
        return user;
    }

    private Country generateCountry() {
        return new Country(1L, "France", 18);
    }

    private Gender generateGender() {
        return new Gender(1L, "Male");
    }
}
