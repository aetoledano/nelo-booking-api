package com.example.nelobookingapi.dto;

public record ApiError(
    String errorCode,
    String message
) {
}
