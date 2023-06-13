package com.example.nelobookingapi.controller;

import com.example.nelobookingapi.dto.ReservationCreateRequest;
import com.example.nelobookingapi.dto.RestaurantFindRequest;
import com.example.nelobookingapi.exceptions.ClientsNotRegisteredException;
import com.example.nelobookingapi.exceptions.NoRestaurantMatchDietaryRequirements;
import com.example.nelobookingapi.exceptions.NoTableAvailablesOnThisRestaurant;
import com.example.nelobookingapi.exceptions.OversizedGroupException;
import com.example.nelobookingapi.exceptions.RestaurantNotFoundWithThatName;
import com.example.nelobookingapi.services.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class BookingController {
    
    @Autowired
    BookingService service;
    
    @Transactional
    @PostMapping("/restaurants")
    ResponseEntity findRestaurants(
        @RequestBody @Valid RestaurantFindRequest request
    ) throws OversizedGroupException, ClientsNotRegisteredException, NoRestaurantMatchDietaryRequirements {
        
        return ResponseEntity.ok(service.findAvailableRestaurants(request.toReservationInfoDto()));
    }
    @Transactional
    @PostMapping("/reservations")
    ResponseEntity makeReservation(
        @RequestBody @Valid ReservationCreateRequest request
    ) throws OversizedGroupException, ClientsNotRegisteredException, NoTableAvailablesOnThisRestaurant, RestaurantNotFoundWithThatName {
        
        return ResponseEntity.ok(service.makeReservation(request.toReservationInfoDto()));
    }
    @Transactional
    @DeleteMapping("/reservations/{reservationId}")
    ResponseEntity removeReservation(
        @PathVariable("reservationId") UUID reservationId
    ) {
        service.removeReservation(reservationId);
        
        return ResponseEntity.noContent().build();
    }
}
