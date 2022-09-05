package com.example.test.entity;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    private Validator validator;

    @BeforeTestClass
    public void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenWellConstructUser_thenNoConstraintViolations() {
        User user = new User(
                1L,
                "Jean-Marie",
                LocalDate.of(1999, Month.JULY, 6),
                "France",
                "0992865854",
                "Male"

        );
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void whenNoDataUser_thenFourConstraintsViolation() {
        User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(4);
    }

    @Test
    public void whenEmptyName_thenOneConstraintViolations() {
        User user = new User(1L,"", LocalDate.of(1999,Month.JANUARY, 9), "France", null, null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }

    // TODO : Other test
}
