package com.example.technicaltest.controller;

import com.example.technicaltest.response.ResponseHandler;
import com.example.technicaltest.service.UserServiceImpl;
import com.example.technicaltest.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/users")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Register user in database
     *
     * @param userDTO Container of all user data
     * @return an ResponseEntity with the created user or a Bad Request status or an Internal Server Error status
     */
    @Operation(summary = "Register a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error",
                    content = @Content)})
    @PostMapping(path = "")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO userCreated = this.userService.createUser(userDTO);
        return ResponseHandler.createResponseEntity("User Created", HttpStatus.CREATED, userCreated);
    }

    /**
     * Fetch a user in database from is id
     *
     * @param id user id
     * @return an ResponseEntity with the corresponding user or a Not Found status or an Internal Server Error status
     */
    @Operation(summary = "Get a User by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error",
                    content = @Content)})
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> fetchUserWithId(@PathVariable Long id) {
        UserDTO userFetched = this.userService.getUserById(id);
        return ResponseHandler.createResponseEntity("", HttpStatus.OK, userFetched);
    }
}
