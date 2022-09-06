package com.example.technicaltest.model;

import com.example.technicaltest.entity.Country;
import com.example.technicaltest.entity.Gender;
import com.example.technicaltest.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelMapperTest {

    private ModelMapper mapper;


    /**
     * Set up the mapper as MapperMaker configure it
     */
    @BeforeEach
    public void setup() {
        mapper = new ModelMapper();

        TypeMap<User, UserDTO> propertyEntityToDTOMapper = mapper.createTypeMap(User.class, UserDTO.class);
        propertyEntityToDTOMapper.addMappings(
                map -> map.map(src -> src.getCountry().getName(), UserDTO::setCountry)
        );
        propertyEntityToDTOMapper.addMappings(
                map -> map.map(src -> src.getGender().getName(), UserDTO::setGender)
        );

        TypeMap<UserDTO, User> propertyDTOToEntityMapper = mapper.createTypeMap(UserDTO.class, User.class);
        propertyDTOToEntityMapper.addMappings(
                map -> map.map(UserDTO::getCountry, User::setCountryFromDTO)
        );
        propertyDTOToEntityMapper.addMappings(
                map -> map.map(UserDTO::getGender, User::setGenderFromDTO)
        );
    }

    @Test
    public void whenMapUserEntityToUserDto_thenExactMatch() {
        User user = new User();
        user.setId(1L);
        user.setName("Jean-Marie");
        user.setBirthdate(LocalDate.of(1999, Month.JANUARY, 3));
        user.setCountry(new Country("France", 18));
        user.setGender(new Gender("Male"));
        user.setPhoneNumber("06952857654");

        UserDTO userDTO = mapper.map(user, UserDTO.class);

        assertThat(user.getId()).isEqualTo(userDTO.getId());
        assertThat(user.getName()).isEqualTo(userDTO.getName());
        assertThat(user.getBirthdate()).isEqualTo(userDTO.getBirthdate());
        assertThat(user.getCountry().getName()).isEqualTo(userDTO.getCountry());
        assertThat(user.getGender().getName()).isEqualTo(userDTO.getGender());
        assertThat(user.getPhoneNumber()).isEqualTo(userDTO.getPhoneNumber());
    }

    @Test
    public void whenMapUserDtoToUserEntity_thenExactMatch() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Jean-Marie");
        userDTO.setBirthdate(LocalDate.of(1999, Month.JANUARY, 3));
        userDTO.setCountry("France");
        userDTO.setGender("Male");
        userDTO.setPhoneNumber("06952857654");

        User user = mapper.map(userDTO, User.class);
        assertThat(userDTO.getId()).isEqualTo(user.getId());
        assertThat(userDTO.getName()).isEqualTo(user.getName());
        assertThat(userDTO.getBirthdate()).isEqualTo(user.getBirthdate());
        assertThat(userDTO.getCountry()).isEqualTo(user.getCountry().getName());
        assertThat(userDTO.getGender()).isEqualTo(user.getGender().getName());
        assertThat(userDTO.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    public void whenMapUserDTOwithNullToUser_thenExactMatch() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(null);
        userDTO.setName(null);
        userDTO.setBirthdate(null);
        userDTO.setCountry(null);
        userDTO.setGender(null);
        userDTO.setPhoneNumber(null);

        User user = mapper.map(userDTO, User.class);
        assertThat(userDTO.getId()).isEqualTo(user.getId());
        assertThat(userDTO.getName()).isEqualTo(user.getName());
        assertThat(userDTO.getBirthdate()).isEqualTo(user.getBirthdate());
        assertThat(userDTO.getCountry()).isEqualTo(user.getCountry().getName());
        assertThat(userDTO.getGender()).isEqualTo(user.getGender().getName());
        assertThat(userDTO.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }
}
