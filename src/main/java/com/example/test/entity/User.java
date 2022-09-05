package com.example.test.entity;

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

    @Column(nullable = false)
    private String countryResidency;

    private String phoneNumber;

    private String gender;

    public User(String name, LocalDate birthdate, String countryResidency, String phoneNumber, String gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.countryResidency = countryResidency;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
