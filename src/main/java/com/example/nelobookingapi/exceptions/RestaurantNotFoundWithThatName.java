package com.example.nelobookingapi.exceptions;

public class RestaurantNotFoundWithThatName extends Exception implements BusinessException {
    
    final int ERROR_CODE = 4044;
    
    @Override
    public String getErrorCode() {
        return String.valueOf(ERROR_CODE);
    }
    
    public RestaurantNotFoundWithThatName(String name) {
        super("Restaurants named " + name + " could not be found");
    }
}
