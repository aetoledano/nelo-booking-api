package com.example.nelobookingapi.exceptions;

import java.util.Collection;

public class NoRestaurantMatchDietaryRequirements extends Exception implements BusinessException {
    
    final int ERROR_CODE = 4041;
    
    @Override
    public String getErrorCode() {
        return String.valueOf(ERROR_CODE);
    }
    
    public NoRestaurantMatchDietaryRequirements(Collection<String> clients) {
        super("No restaurant match dietary requirements for clients " + clients);
    }
}
