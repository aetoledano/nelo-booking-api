package com.example.nelobookingapi.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ReservationCreateRequest {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    
    int startTime;
    
    String[] diners;
    
    String restaurant;
    
    public ReservationInfoDto toReservationInfoDto() {
        return new ReservationInfoDto(
            startTime,
            startTime + 2000,
            date = date,
            List.of(diners),
            restaurant
        );
    }
}
