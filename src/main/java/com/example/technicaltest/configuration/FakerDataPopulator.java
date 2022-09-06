package com.example.technicaltest.configuration;

import com.example.technicaltest.entity.Gender;
import com.example.technicaltest.entity.User;
import com.example.technicaltest.entity.Country;
import com.example.technicaltest.repository.CountryDAO;
import com.example.technicaltest.repository.GenderDAO;
import com.example.technicaltest.repository.UserDAO;
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

    private final UserDAO userDAO;
    private final CountryDAO countryDAO;
    private final GenderDAO genderDAO;

    /**
     * Database populator constructor
     *
     * @param userDAO    the User Repository
     * @param countryDAO
     * @param genderDAO
     */
    public FakerDataPopulator(
            UserDAO userDAO,
            CountryDAO countryDAO,
            GenderDAO genderDAO) {
        this.userDAO = userDAO;
        this.countryDAO = countryDAO;
        this.genderDAO = genderDAO;
    }

    /**
     * Initiate database data with fake data
     */
    @Override
    public void run(String... args) {

        Country france = new Country("France", 18);
        countryDAO.save(france);

        Gender male = new Gender("Male");
        Gender female = new Gender("Female");
        Gender other = new Gender("Other");
        genderDAO.saveAll(List.of(male, female, other));

        User jean = new User(
                "Jean",
                LocalDate.of(2000, Month.JANUARY, 27),
                france,
                "+33695642584",
                male
        );
        User max = new User(
                "Max",
                LocalDate.of(2001, Month.FEBRUARY, 26),
                france,
                "0033695632145",
                female
        );
        User luli = new User(
                "Luli",
                LocalDate.of(2002, Month.MARCH, 25),
                france,
                "0584966235",
                other
        );
        userDAO.saveAll(
                List.of(max, jean, luli)
        );
    }
}
