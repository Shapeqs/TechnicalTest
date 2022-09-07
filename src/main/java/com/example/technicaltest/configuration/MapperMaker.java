package com.example.technicaltest.configuration;

import com.example.technicaltest.entity.User;
import com.example.technicaltest.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to autowire ModelMapper everywhere
 * @author Clement Querre
 */
@Configuration
public class MapperMaker {

    /**
     * Create a ModelMapper with a custom mapping for entity and model User
     * @return new ModelMapper with the correct custom mapping
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

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

        return mapper;
    }
}
