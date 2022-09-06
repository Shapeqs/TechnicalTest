package com.example.technicaltest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;

    @Schema(example = "Jean")
    private String name;

    @Schema(example = "1999-01-02")
    private LocalDate birthdate;

    @Schema(example = "France")
    private String country;

    @Schema(example = "+33 6 99 55 66 32")
    private String phoneNumber;

    @Schema(example = "Male / Female / Other")
    private String gender;
}
