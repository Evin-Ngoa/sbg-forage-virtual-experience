package com.evin.tms.authservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
/**
 * Data Transfer Object (DTO) for login requests.
 * This class is used to transfer login data, such as username and password, from the client to the server.
 */
public class LoginDto {

    /**
     * The username of the user attempting to log in.
     * This field is annotated with validation constraints to ensure it is not null and not empty.
     * The messages provide user-friendly error feedback.
     */
    @NotNull(message = "Username must not be null")
    @NotEmpty(message = "Username must not be empty")
    private String username;

    /**
     * The password of the user attempting to log in.
     * Similar to the username, this field also has validation constraints to ensure it is not null and not empty.
     * The messages help in providing clear feedback to the user in case of invalid input.
     */
    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
