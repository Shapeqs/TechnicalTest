package com.example.test.model;

import com.example.technicaltest.entity.Country;
import com.example.technicaltest.entity.User;
import com.example.technicaltest.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class UserDTOTest {
    private final ModelMapper mapper = new ModelMapper();

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        User user = new User();
        user.setId(1L);
        user.setName("Jean-Marie");
        user.setBirthdate(LocalDate.of(1999, Month.JANUARY, 3));
        Country france = new Country("France");
        user.setCountryResidency(france);
        user.setGender("Male");
        user.setPhoneNumber("06952857654");

        UserDTO userDTO = mapper.map(user, UserDTO.class);
        assertThat(user.getId()).isEqualTo(userDTO.getId());
        assertThat(user.getName()).isEqualTo(userDTO.getName());
        assertThat(user.getBirthdate()).isEqualTo(userDTO.getBirthdate());
        assertThat(user.getCountryResidency().getName()).isEqualTo(userDTO.getCountryResidency());
        assertThat(user.getGender()).isEqualTo(userDTO.getGender());
        assertThat(user.getPhoneNumber()).isEqualTo(userDTO.getPhoneNumber());
    }

    @Test
    public void whenConvertUserDtoToUserEntity_thenCorrect() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Jean-Marie");
        userDTO.setBirthdate(LocalDate.of(1999, Month.JANUARY, 3));
        userDTO.setCountryResidency("France");
        userDTO.setGender("Male");
        userDTO.setPhoneNumber("06952857654");

        User user = mapper.map(userDTO, User.class);
        assertThat(userDTO.getId()).isEqualTo(user.getId());
        assertThat(userDTO.getName()).isEqualTo(user.getName());
        assertThat(userDTO.getBirthdate()).isEqualTo(user.getBirthdate());
        assertThat(userDTO.getCountryResidency()).isEqualTo(user.getCountryResidency().getName());
        assertThat(userDTO.getGender()).isEqualTo(user.getGender());
        assertThat(userDTO.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }
}
