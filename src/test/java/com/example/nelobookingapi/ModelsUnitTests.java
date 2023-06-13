package com.example.nelobookingapi;

import com.example.nelobookingapi.exceptions.OversizedGroupException;
import org.junit.jupiter.api.Test;

import static com.example.nelobookingapi.models.ModelHelper.tablesThatCanHoldGroupofSize;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModelsUnitTests {
    @Test
    void TestModelHelper() throws OversizedGroupException {
        var small = new int[]{2, 4, 6};
        var medium = new int[]{4, 6};
        var big = new int[]{6};
        
        assertArrayEquals(small, tablesThatCanHoldGroupofSize(1));
        assertArrayEquals(small, tablesThatCanHoldGroupofSize(2));
        assertArrayEquals(medium, tablesThatCanHoldGroupofSize(3));
        assertArrayEquals(medium, tablesThatCanHoldGroupofSize(4));
        assertArrayEquals(big, tablesThatCanHoldGroupofSize(5));
        assertArrayEquals(big, tablesThatCanHoldGroupofSize(6));
        
        assertThrows(OversizedGroupException.class, () -> tablesThatCanHoldGroupofSize(7));
    }
}
