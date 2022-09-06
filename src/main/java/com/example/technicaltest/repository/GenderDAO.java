package com.example.technicaltest.repository;

import com.example.technicaltest.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderDAO extends JpaRepository<Gender, Long> {
    Gender findByName(String name);
}
