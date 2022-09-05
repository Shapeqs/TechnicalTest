package com.example.test.config;

import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner populateDatabase(UserRepository userRepository) {
        return args -> {
            User jean = new User(
                    "Jean",
                    LocalDate.of(2000, Month.JANUARY, 27),
                    "France",
                    "+33695642584",
                    "male"
            );
            User max = new User(
                    "Max",
                    LocalDate.of(2001, Month.FEBRUARY, 26),
                    "France",
                    "0033695632145",
                    "other"
            );
            User luli = new User(
                    "Luli",
                    LocalDate.of(2002, Month.MARCH, 25),
                    "France",
                    "0584966235",
                    "female"
            );
            userRepository.saveAll(
                    List.of(max, jean, luli)
            );
        };
    }
}
