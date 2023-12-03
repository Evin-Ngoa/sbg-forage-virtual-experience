package com.evin.tms.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic class for API responses.
 * This class is used to create a standardized format for responses sent from the server to the client.
 * It includes the response status, a message, and any associated data.
 *
 * @param <T> The type of data included in the response.
 */
@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all arguments.
@NoArgsConstructor  // Lombok annotation to automatically generate a no-argument constructor.
public class ApiResponse<T> {

    /**
     * The HTTP status code of the response.
     */
    private int status;

    /**
     * A user-friendly message providing more details about the response.
     */
    private String message;

    /**
     * The data payload of the response. Its type is generic and can be anything relevant to the specific response.
     */
    private T data;
}

