package com.example.technicaltest.controller;

import com.example.technicaltest.model.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestInt {

    private final String URL = "/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenCreatingUser_ThenReturnUserWith201Status() throws Exception {
        UserDTO userDTO = generateUser();
        String jsonRequest = mapper.writeValueAsString(userDTO);

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").isNumber());
    }

    @Test
    public void whenFetchingUser_ThenReturnUserWith200Status() throws Exception {
        final Long id = 8L;
        UserDTO userDTO = generateUser();
        userDTO.setId(id);
        String content = mapper.writeValueAsString(userDTO);
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(URL + "/{id}", id)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createUserWithInvalidName_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setName(" ");
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "You must put a name");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithNullBirthdate_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setBirthdate(null);
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "Birthdate cannot be null");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithInvalidBirthdate_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setBirthdate(LocalDate.now());
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "You must be an adult in your country to register");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithNullCountry_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setCountry(null);
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "Country residency cannot be null");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithInvalidCountry_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setCountry("Italy");
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "Only French residents can register");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithInvalidGender_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setGender("Nope");
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "Gender must be female, male or other");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    @Test
    public void createUserWithInvalidPhoneNumber_thenReturn400Status() throws Exception {
        UserDTO userDTO = generateUser();
        userDTO.setPhoneNumber("2313");
        String jsonRequest = mapper.writeValueAsString(userDTO);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", null);
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("message", "Invalid phone number format");

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse.toString()));
    }

    private UserDTO generateUser() {
        UserDTO user = new UserDTO();
        user.setName("Tester1");
        user.setBirthdate(LocalDate.of(1999, Month.FEBRUARY, 1));
        user.setCountry("France");
        user.setGender("Male");
        user.setPhoneNumber("+33695285727");
        return user;
    }

    private JSONObject generateValidJsonUser() {
        JSONObject userData = new JSONObject();
        userData.put("id", 8);
        userData.put("name", "Tester1");
        userData.put("birthdate", "1999-02-01");
        userData.put("country", "France");
        userData.put("gender", "Male");
        userData.put("phoneNumber", "+33695285727");
        return userData;
    }
}
