package com.example.nelobookingapi.dto;

import java.time.LocalDate;

public record ReservationInfoDto(
    int startTime,
    int endTime,
    LocalDate date,
    String[] clientNames
) {
}
