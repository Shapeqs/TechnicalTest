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
     * Create a ModelMapper
     * @return new ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        TypeMap<User, UserDTO> propertyEntityToDTOMapper = mapper.createTypeMap(User.class, UserDTO.class);
        propertyEntityToDTOMapper.addMappings(
                map -> map.map(src -> src.getCountryResidency().getName(), UserDTO::setCountryResidency)
        );
        propertyEntityToDTOMapper.addMappings(
                map -> map.map(src -> src.getGender().getName(), UserDTO::setGender)
        );

        TypeMap<UserDTO, User> propertyDTOToEntityMapper = mapper.createTypeMap(UserDTO.class, User.class);
        propertyDTOToEntityMapper.addMappings(
                map -> map.map(UserDTO::getCountryResidency, User::setCountryResidencyFromDTO)
        );
        propertyDTOToEntityMapper.addMappings(
                map -> map.map(UserDTO::getGender, User::setGenderFromDTO)
        );

        return mapper;
    }
}
