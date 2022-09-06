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
     * Create a new country object from a name
     * @param name the country name
     */
    public Country(String name) {
        this.name = name;
    }
}
