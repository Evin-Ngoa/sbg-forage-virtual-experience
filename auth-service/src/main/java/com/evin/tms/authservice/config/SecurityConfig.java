package com.evin.tms.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
/**
 * Security configuration class for the application.
 * This class configures the security aspects of the application, particularly
 * the rules for the web security filter chain.
 */
public class SecurityConfig {

    /**
     * Configures the SecurityFilterChain.
     *
     * @param http HttpSecurity object used to configure the security filter chain.
     * @return A SecurityFilterChain that dictates the security policy.
     * @throws Exception In case of any configuration errors.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HttpSecurity
        return http
                // Disable CSRF protection. Useful in stateless/session-less applications like REST APIs
                .csrf().disable()
                // Configure authorization of HTTP requests
                .authorizeHttpRequests()
                // Allow all requests to the "/authenticate" endpoint without authentication
                .requestMatchers("/authenticate").permitAll()
                // Finalize the security filter chain configuration
                .and()
                .build();
    }
}

