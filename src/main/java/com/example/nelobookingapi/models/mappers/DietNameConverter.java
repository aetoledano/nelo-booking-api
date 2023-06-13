package com.example.nelobookingapi.models.mappers;

import com.example.nelobookingapi.models.DietName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DietNameConverter implements AttributeConverter<DietName, String> {
    @Override
    public String convertToDatabaseColumn(DietName dietName) {
        return dietName != null ? dietName.name() : null;
    }
    
    @Override
    public DietName convertToEntityAttribute(String s) {
        return s != null ? DietName.valueOf(s) : null;
    }
}
