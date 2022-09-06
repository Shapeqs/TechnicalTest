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
public class Gender {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * Create a new gender object from a name
     * @param name the gender name
     */
    public Gender(String name) {
        this.name = name;
    }
}
