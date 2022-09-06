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

    public User(String name, LocalDate birthdate, Country country, String phoneNumber, Gender gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public void setCountryFromDTO(String name) {
        this.country = new Country(name, 0);
    }

    public void setGenderFromDTO(String name) {
        this.gender = new Gender(name);
    }
}
