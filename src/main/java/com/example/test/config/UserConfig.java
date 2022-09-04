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
    CommandLineRunner populateDatabase(UserRepository repository) {
        return args -> {
            User jean = new User(
                    "Jean",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "FRANCE",
                    "06952857",
                    "MALE"
            );
            User max = new User(
                    "Max",
                    LocalDate.of(2001, Month.JANUARY, 5),
                    "FRANCE",
                    "06952857",
                    "MALE"
            );
            User luli = new User(
                    "Luli",
                    LocalDate.of(2001, Month.JANUARY, 5),
                    "FRANCE",
                    "06952857",
                    "FEMALE"
            );
            repository.saveAll(
                    List.of(max, jean, luli)
            );
        };
    }
}
