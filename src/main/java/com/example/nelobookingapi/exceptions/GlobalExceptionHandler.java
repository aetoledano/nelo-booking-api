package com.example.nelobookingapi.exceptions;

import com.example.nelobookingapi.dto.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ClientsNotRegisteredException.class)
    public ResponseEntity handle(ClientsNotRegisteredException ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            ex.getErrorCode(),
            ex.getMessage()
        ));
    }
    
    @ExceptionHandler(NoRestaurantMatchDietaryRequirements.class)
    public ResponseEntity handle(NoRestaurantMatchDietaryRequirements ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            ex.getErrorCode(),
            ex.getMessage()
        ));
    }
    
    @ExceptionHandler(NoTableAvailablesOnThisRestaurant.class)
    public ResponseEntity handle(NoTableAvailablesOnThisRestaurant ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            ex.getErrorCode(),
            ex.getMessage()
        ));
    }
    
    @ExceptionHandler(OversizedGroupException.class)
    public ResponseEntity handle(OversizedGroupException ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            ex.getErrorCode(),
            ex.getMessage()
        ));
    }
    
    @ExceptionHandler(RestaurantNotFoundWithThatName.class)
    public ResponseEntity handle(RestaurantNotFoundWithThatName ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            ex.getErrorCode(),
            ex.getMessage()
        ));
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handle(DataIntegrityViolationException ex) {
        
        return ResponseEntity.status(400).body(new ApiError(
            HttpStatus.CONFLICT.name(),
            ex.getMessage()
        ));
    }
    
}
