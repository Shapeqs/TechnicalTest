package com.example.technicaltest.repository;

import com.example.technicaltest.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDAO extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
