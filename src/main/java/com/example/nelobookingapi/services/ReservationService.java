package com.example.nelobookingapi.services;

import com.example.nelobookingapi.dto.ReservationInfoDto;
import com.example.nelobookingapi.exceptions.ClientsNotRegisteredException;
import com.example.nelobookingapi.exceptions.NoRestaurantMatchDietaryRequirements;
import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.Restaurant;
import com.example.nelobookingapi.repositories.ClientRepo;
import com.example.nelobookingapi.repositories.ReservationRepo;
import com.example.nelobookingapi.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService {

//    @Autowired
//    ReservationRepo reservationRepo;
//
//    @Autowired
//    ClientRepo clientRepo;
//
//    @Autowired
//    RestaurantRepo restaurantRepo;
//
//    public List<Restaurant> findAvailableRestaurants(ReservationInfoDto info) throws ClientsNotRegisteredException, NoRestaurantMatchDietaryRequirements {
//        var clients = findClients(info);
//        var restaurants = findRestaurantsWithDietaryRestrictions(clients);
//
//        return findReservations(clients, restaurants, info);
//    }
//
//    private List<Restaurant> findReservations(List<Client> clients, List<Restaurant> restaurants, ReservationInfoDto info) {
//
//    }
//
//    private List<Client> findClients(ReservationInfoDto info) throws ClientsNotRegisteredException {
//        var clients = clientRepo.findAllByNames(info.clientNames());
//
//        if (clients.size() != info.clientNames().size()) {
//            var present = clients.stream().map(Client::getName).collect(Collectors.toSet());
//            var all = new HashSet<>(info.clientNames());
//            all.removeAll(present);
//            throw new ClientsNotRegisteredException(all);
//        }
//
//        return clients;
//    }
//
//    private List<Restaurant> findRestaurantsWithDietaryRestrictions(List<Client> clients) throws NoRestaurantMatchDietaryRequirements {
//        Set<Long> dgs = clients.stream()
//            .flatMap(
//                c -> c.getRestrictions().stream()
//            ).map(
//                dg -> dg.getId()
//            ).collect(Collectors.toSet());
//
//        if (dgs.isEmpty()) return restaurantRepo.findAll();
//
//        var restaurants = restaurantRepo.findAllByDietRestrictions(dgs, dgs.size());
//
//        if (restaurants.isEmpty()) throw new NoRestaurantMatchDietaryRequirements(
//            clients.stream().map(Client::getName).collect(Collectors.toSet())
//        );
//
//        return restaurants;
//    }
//
//    public List<Restaurant> makeReservation(ReservationInfoDto info) {
//
//    }
}
