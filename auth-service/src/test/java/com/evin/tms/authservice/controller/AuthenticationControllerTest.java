package com.evin.tms.authservice.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.evin.tms.authservice.controller.AuthenticationController;
import com.evin.tms.authservice.dto.LoginDto;
import com.evin.tms.authservice.exception.ApiResponse;
import com.evin.tms.authservice.service.AuthService;
import com.evin.tms.authservice.service.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthenticationController controller;

    @Test
    public void whenValidCredentials_thenAuthenticateAndGenerateToken() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("testuserpassword");
        when(authService.authenticate("testuser", "testuserpassword")).thenReturn(true);
        when(jwtUtil.generateToken(loginDto)).thenReturn("token");

        // Act
        ResponseEntity<ApiResponse<String>> response = controller.createAuthenticationToken(loginDto);

        // Assert
        assertEquals(HttpStatus.OK.value(), response.getBody().getStatus());
        assertEquals("Request Sent Successfully", response.getBody().getMessage());
        assertEquals("token", response.getBody().getData());
    }

    @Test
    public void whenInvalidCredentials_thenAuthenticationFails() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("wrongpassword");
        when(authService.authenticate("testuser", "wrongpassword")).thenReturn(false);

        // Act
        ResponseEntity<ApiResponse<String>> response = controller.createAuthenticationToken(loginDto);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getBody().getStatus());
        assertEquals("Authentication Failed", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    public void whenExceptionOccurs_thenHandleException() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("");
        loginDto.setPassword("wrongpassword");
        when(authService.authenticate("", "wrongpassword")).thenThrow(new RuntimeException("Error occurred"));

        // Act
        ResponseEntity<ApiResponse<String>> response = controller.createAuthenticationToken(loginDto);

        // Assert
        assertEquals(500, response.getBody().getStatus());
        assertTrue(response.getBody().getMessage().contains("Error"));
        assertNull(response.getBody().getData());
    }
}
