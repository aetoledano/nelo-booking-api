package com.example.nelobookingapi.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class ReservationCreateRequest {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    
    @NotNull
    Integer startTime;
    
    @NotEmpty
    String[] diners;
    
    @NotBlank
    String restaurant;
    
    public ReservationCreateRequest(LocalDate date, Integer startTime, @NotEmpty String[] diners, String restaurant) {
        this.date = date;
        this.startTime = startTime;
        this.diners = diners;
        this.restaurant = restaurant;
    }
    
    public ReservationInfoDto toReservationInfoDto() {
        return new ReservationInfoDto(
            startTime,
            startTime + 200,
            date = date,
            List.of(diners),
            restaurant
        );
    }
}
