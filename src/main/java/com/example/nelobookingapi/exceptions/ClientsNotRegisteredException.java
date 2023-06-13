package com.example.nelobookingapi.exceptions;

import java.util.Collection;

public class ClientsNotRegisteredException extends Exception implements BusinessException {
    
    final int ERROR_CODE = 4042;
    
    @Override
    public String getErrorCode() {
        return String.valueOf(ERROR_CODE);
    }
    
    public ClientsNotRegisteredException(Collection<String> clients) {
        super("Clients not registered " + clients);
    }
}
