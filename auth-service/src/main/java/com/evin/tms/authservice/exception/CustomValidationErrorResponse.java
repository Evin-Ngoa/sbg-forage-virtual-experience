package com.evin.tms.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Class representing a custom response structure for validation errors.
 * This class is used to send detailed information about validation failures in API requests.
 */
@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
@AllArgsConstructor // Lombok annotation to automatically generate a constructor with all arguments.
public class CustomValidationErrorResponse {

    /**
     * The HTTP status code associated with the validation error.
     * Typically, this would be set to a client error code like 400 (Bad Request).
     */
    private int status;

    /**
     * A general message about the nature of the validation errors.
     * This could be a generic message like "Validation error" or something more specific.
     */
    private String message;

    /**
     * A list of individual validation errors.
     * Each error provides detailed information about a specific validation failure.
     */
    private List<ValidationError> errors; // Assuming ValidationError is a custom class representing individual validation errors.
}

