package com.example.nelobookingapi.exceptions;

import java.util.Collection;

public class OversizedGroupException extends Exception implements BusinessException {
    
    final int ERROR_CODE = 4003;
    
    @Override
    public String getErrorCode() {
        return String.valueOf(ERROR_CODE);
    }
    
    public OversizedGroupException() {
        super("Restaurants could not make reservations for groups of people greater than 6");
    }
}
