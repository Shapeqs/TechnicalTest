package com.example.technicaltest.service;

import com.example.technicaltest.entity.Country;
import com.example.technicaltest.entity.Gender;
import com.example.technicaltest.entity.User;
import com.example.technicaltest.exception.UserNotFoundException;
import com.example.technicaltest.model.UserDTO;
import com.example.technicaltest.repository.CountryDAO;
import com.example.technicaltest.repository.GenderDAO;
import com.example.technicaltest.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        assertThatThrownBy(() ->
                service.getUserById(id)
        ).isInstanceOf(UserNotFoundException.class)
                .hasMessage("User do not exist");
    }

    @Test
    public void creatingUser_thenUserIsReturned() {
        final User user = generateUser();
        final UserDTO userDTO = mapper.map(user, UserDTO.class);

        given(countryDAO.findByName(any())).willReturn(generateCountry());
        given(genderDAO.findByName(any())).willReturn(generateGender());
        given(userDAO.save(any())).willReturn(user);

        UserDTO created = this.service.createUser(userDTO);
        assertThat(created.getName()).isEqualTo(userDTO.getName());
        assertThat(created.getBirthdate()).isEqualTo(userDTO.getBirthdate());
        assertThat(created.getCountry()).isEqualTo(userDTO.getCountry());
        assertThat(created.getGender()).isEqualTo(userDTO.getGender());
        assertThat(created.getPhoneNumber()).isEqualTo(userDTO.getPhoneNumber());
    }

    private User generateUser() {
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
