package com.example.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String countryResidency;
    private String phoneNumber;
    private String gender;

    public User(Long id,
                String name,
                LocalDate birthdate,
                String countryResidency,
                String phoneNumber,
                String gender) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.countryResidency = countryResidency;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public User(String name,
                LocalDate birthdate,
                String countryResidency,
                String phoneNumber,
                String gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.countryResidency = countryResidency;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountryResidency() {
        return countryResidency;
    }

    public void setCountryResidency(String contryResidency) {
        this.countryResidency = contryResidency;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", contryResidency='" + countryResidency + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
