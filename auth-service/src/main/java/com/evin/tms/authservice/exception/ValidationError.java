package com.evin.tms.authservice.exception;

import lombok.Data;

/**
 * Class representing details of a validation error.
 * This class is used to encapsulate specific information about each validation failure
 * encountered during the processing of a request.
 */
@Data // Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString methods.
public class ValidationError {

    /**
     * The specific validation error code.
     * This could be an identifier like 'NotNull', 'Size', etc., representing the type of validation that failed.
     */
    private String code;

    /**
     * The name of the field that failed validation.
     * This indicates which specific part of the request data did not pass validation.
     */
    private String field;

    /**
     * The value that was rejected during validation.
     * This holds the actual value provided by the user that failed to meet the validation criteria.
     */
    private Object rejectedValue;

    /**
     * A user-friendly error message describing why the validation failed.
     * This message can be used to inform the user about the nature of the validation error.
     */
    private String message;
}

