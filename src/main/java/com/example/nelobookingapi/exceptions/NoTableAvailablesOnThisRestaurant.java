package com.example.nelobookingapi.exceptions;

public class NoTableAvailablesOnThisRestaurant extends Exception implements BusinessException {
    
    final int ERROR_CODE = 4001;
    
    @Override
    public String getErrorCode() {
        return String.valueOf(ERROR_CODE);
    }
    
    public NoTableAvailablesOnThisRestaurant(String name) {
        super("Restaurant named " + name + " does not have tables available");
    }
}
