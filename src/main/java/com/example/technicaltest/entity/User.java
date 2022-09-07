package com.example.technicaltest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    /**
     * Constructor of User Object to help initiate fake data into database
     * @param name the name of the User
     * @param birthdate the birthdate of the User
     * @param country the country of the User
     * @param phoneNumber the phoneNumber of the User
     * @param gender the gender of the User
     */
    public User(String name, LocalDate birthdate, Country country, String phoneNumber, Gender gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    /**
     * Specific setter to help build User from UserDTO
     * @param name the country contained in UserDTO
     */
    public void setCountryFromDTO(String name) {
        this.country = new Country(name, 0);
    }

    /**
     * Specific setter to help build User from UserDTO
     * @param name the gender contained in UserDTO
     */
    public void setGenderFromDTO(String name) {
        this.gender = new Gender(name);
    }
}
