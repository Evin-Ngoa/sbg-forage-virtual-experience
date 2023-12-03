package com.evin.tms.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for handling validation errors across the entire application.
 * This class intercepts exceptions related to method argument validation and provides a custom response.
 */
@ControllerAdvice
public class CustomValidationExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException thrown when @Validated annotated method parameters fail validation.
     *
     * @param ex The exception instance containing details about the validation failure.
     * @return A ResponseEntity containing a custom validation error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();

        // Loop through all validation errors and create a list of custom ValidationError objects
        ex.getBindingResult().getAllErrors().forEach(error -> {
            ValidationError ve = new ValidationError();
            ve.setCode(error.getCode()); // Sets the specific error code (e.g., NotNull, Size)
            ve.setField(((FieldError) error).getField()); // Sets the name of the field that failed validation
            ve.setRejectedValue(((FieldError) error).getRejectedValue()); // Sets the value that was rejected during validation
            ve.setMessage(error.getDefaultMessage()); // Sets the default error message
            errors.add(ve);
        });

        // Create a custom error response object
        CustomValidationErrorResponse errorResponse = new CustomValidationErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
        // Return the custom error response with a BAD_REQUEST status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

