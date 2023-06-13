package com.example.nelobookingapi.models;

import com.example.nelobookingapi.exceptions.OversizedGroupException;

public class ModelHelper {
    
    public static int[] tablesThatCanHoldGroupofSize(int size) throws OversizedGroupException {
        if (size <= 2) return new int[]{2, 4, 6};
        else if (size <= 4) return new int[]{4, 6};
        else if (size <= 6) return new int[]{6};
        
        throw new OversizedGroupException();
    }
}
