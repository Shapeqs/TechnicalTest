package com.example.test.configuration;

import org.modelmapper.ModelMapper;
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
        return new ModelMapper();
    }
}
