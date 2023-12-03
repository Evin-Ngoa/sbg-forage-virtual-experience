package com.evin.tms.authservice.service;

/**
 * Interface defining the authentication service.
 * This service provides methods for user authentication.
 */
public interface AuthService {

     /**
      * Authenticates a user based on the provided username and password.
      *
      * @param username The username of the user to be authenticated.
      * @param password The password of the user to be authenticated.
      * @return true if authentication is successful, false otherwise.
      */
     boolean authenticate(String username, String password);
}
