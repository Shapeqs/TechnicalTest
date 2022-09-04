package com.example.test.model;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String countryResidency;
    private String gender;
    private String phoneNumber;

    public UserDTO() {
    }

    public UserDTO(Long id,
                   String name,
                   LocalDate birthdate,
                   String countryResidency,
                   String gender,
                   String phoneNumber) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.countryResidency = countryResidency;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(String name,
                   LocalDate birthdate,
                   String countryResidency,
                   String gender,
                   String phoneNumber) {
        this.name = name;
        this.birthdate = birthdate;
        this.countryResidency = countryResidency;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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

    public void setCountryResidency(String countryResidency) {
        this.countryResidency = countryResidency;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
