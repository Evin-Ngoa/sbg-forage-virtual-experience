package com.evin.tms.authservice.controller;

import com.evin.tms.authservice.dto.LoginDto;
import com.evin.tms.authservice.exception.ApiResponse;
import com.evin.tms.authservice.service.AuthService;
import com.evin.tms.authservice.service.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthService authService;

    @PostMapping("/authenticate")

    /**
     * Handles the authentication request.
     * @param loginDto Contains the username and password for authentication.
     * @return ResponseEntity with ApiResponse containing the authentication result.
     */
    public ResponseEntity<ApiResponse<String>> createAuthenticationToken(@Valid @RequestBody LoginDto loginDto) {
        // Try block to handle any exceptions that may occur during authentication
        try {
            // Authenticating the user with provided credentials
            boolean isAuthenticated = authService.authenticate(loginDto.getUsername(), loginDto.getPassword());

            // Check if authentication failed
            if (!isAuthenticated) {
                // Create a response indicating authentication failure
                ApiResponse<String> response = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Authentication Failed", null);
                // Return response with 401 Unauthorized status
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // Generate JWT token if authentication is successful
            String token = jwtUtil.generateToken(loginDto);
            // Create a successful ApiResponse object
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Request Sent Successfully", token);
            // Return response with 200 OK status
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Create a response indicating an internal server error
            ApiResponse<String> response = new ApiResponse<>(500, "Error: " + e.getMessage(), null);
            // Return response with 500 Internal Server Error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
