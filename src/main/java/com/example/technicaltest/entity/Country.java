package com.example.technicaltest.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Country {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * Age of Majority set in the country as every country set their own majority age
     */
    @Column(nullable = false)
    private int ageOfMajority;

    /**
     * Create a new country object from a name and an age of majority
     * @param name the country name
     * @param ageOfMajority the age of majority in the country
     */
    public Country(String name, int ageOfMajority) {
        this.name = name;
        this.ageOfMajority = ageOfMajority;
    }
}
