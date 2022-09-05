package com.example.test.configuration;

import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Configuration class to initialise database with fake data
 * @author Clement Querre
 */
@Configuration
public class FakerDataPopulator implements CommandLineRunner {

    private final UserRepository repository;

    /**
     * Database populator constructor
     * @param repository the User Repository
     */
    public FakerDataPopulator(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Initiate database data with fake data
     */
    @Override
    public void run(String... args) {
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
        repository.saveAll(
                List.of(max, jean, luli)
        );
    }
}
