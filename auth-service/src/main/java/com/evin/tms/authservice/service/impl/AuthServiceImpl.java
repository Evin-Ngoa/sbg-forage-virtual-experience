package com.evin.tms.authservice.service.impl;

import com.evin.tms.authservice.dto.LoginDto;
import com.evin.tms.authservice.exception.ApiResponse;
import com.evin.tms.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service implementation for user authentication.
 * This class provides the logic to authenticate users against stored credentials.
 */
@Service
public class AuthServiceImpl implements AuthService {

    /**
     * The hardcoded username.
     * In a real-world application, this should be replaced with a secure mechanism,
     * like retrieving credentials from a database.
     */
    @Value("${user.username}")
    private String hardcodedUsername;

    /**
     * The hardcoded password.
     * For security, passwords should not be stored or used in plain text in production.
     * Instead, use encrypted or hashed forms.
     */
    @Value("${user.password}")
    private String hardcodedPassword;

    /**
     * Authenticates a user based on provided username and password.
     *
     * @param username The username of the user to be authenticated.
     * @param password The password of the user to be authenticated.
     * @return true if the provided credentials match the stored ones, false otherwise.
     */
    @Override
    public boolean authenticate(String username, String password) {
        // Compares the provided credentials with the hardcoded ones
        // In real-world applications, implement password hashing and verification here
        return hardcodedUsername.equals(username) && hardcodedPassword.equals(password);
    }
}

