package com.example.nelobookingapi.dto;

import java.time.LocalDate;
import java.util.Collection;

public record ReservationInfoDto(
    int startTime,
    int endTime,
    LocalDate date,
    Collection<String> clientNames,
    String restaurantName
) {
}
