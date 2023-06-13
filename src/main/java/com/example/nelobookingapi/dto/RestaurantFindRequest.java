package com.example.nelobookingapi.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class RestaurantFindRequest {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    
    int startTime;
    
    String[] diners;
    
    public RestaurantFindRequest(LocalDate date, int startTime, String[] diners) {
        this.date = date;
        this.startTime = startTime;
        this.diners = diners;
    }
    
    public ReservationInfoDto toReservationInfoDto() {
        return new ReservationInfoDto(
            startTime,
            startTime + 2000,
            date = date,
            List.of(diners),
            null
        );
    }
}
