package com.example.nelobookingapi.services;

import com.example.nelobookingapi.dto.ReservationInfoDto;
import com.example.nelobookingapi.exceptions.ClientsNotRegisteredException;
import com.example.nelobookingapi.exceptions.NoRestaurantMatchDietaryRequirements;
import com.example.nelobookingapi.exceptions.NoTableAvailablesOnThisRestaurant;
import com.example.nelobookingapi.exceptions.OversizedGroupException;
import com.example.nelobookingapi.exceptions.RestaurantNotFoundWithThatName;
import com.example.nelobookingapi.models.Client;
import com.example.nelobookingapi.models.ClientReservationData;
import com.example.nelobookingapi.models.Reservation;
import com.example.nelobookingapi.models.Restaurant;
import com.example.nelobookingapi.repositories.ClientRepo;
import com.example.nelobookingapi.repositories.ClientReservationDataRepo;
import com.example.nelobookingapi.repositories.ReservationRepo;
import com.example.nelobookingapi.repositories.RestaurantRepo;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.nelobookingapi.models.ModelHelper.tablesThatCanHoldGroupofSize;

@Service
public class BookingService {
    
    @Autowired
    ReservationRepo reservationRepo;
    
    @Autowired
    ClientRepo clientRepo;
    
    @Autowired
    RestaurantRepo restaurantRepo;
    
    @Autowired
    ClientReservationDataRepo clientReservationDataRepo;
    
    public List<Restaurant> findAvailableRestaurants(ReservationInfoDto info) throws ClientsNotRegisteredException, NoRestaurantMatchDietaryRequirements, OversizedGroupException {
        var clients = findClients(info);
        var restaurants = findRestaurantsWithDietaryRestrictions(clients);
        
        return findAvailableRestaurants(clients, restaurants, info);
    }
    
    private List<Restaurant> findAvailableRestaurants(
        List<Client> clients,
        List<Restaurant> restaurants,
        ReservationInfoDto info
    ) throws OversizedGroupException {
        var tablesSizes = tablesThatCanHoldGroupofSize(clients.size());
        
        var list = new ArrayList<Restaurant>();
        
        for (Restaurant r : restaurants) {
            for (int tableSize : tablesSizes) {
                var occupied = reservationRepo.findOccupiedTablesOfSize(
                    r.getId(),
                    info.date(),
                    tableSize,
                    info.startTime(),
                    info.endTime()
                );
                
                if (r.tablesAvailableOfSize(tableSize, occupied) > 0) {
                    list.add(r);
                    break;
                }
            }
        }
        
        return list;
    }
    
    private List<Client> findClients(ReservationInfoDto info) throws ClientsNotRegisteredException {
        var clients = clientRepo.findAllByNames(info.clientNames());
        
        if (clients.size() != info.clientNames().size()) {
            var present = clients.stream().map(Client::getName).collect(Collectors.toSet());
            var all = new HashSet<>(info.clientNames());
            all.removeAll(present);
            throw new ClientsNotRegisteredException(all);
        }
        
        return clients;
    }
    
    private List<Restaurant> findRestaurantsWithDietaryRestrictions(List<Client> clients) throws NoRestaurantMatchDietaryRequirements {
        Set<Long> dgs = clients.stream()
            .flatMap(
                c -> c.getRestrictions().stream()
            ).map(
                dg -> dg.getId()
            ).collect(Collectors.toSet());
        
        if (dgs.isEmpty()) return restaurantRepo.findAll();
        
        var restaurants = restaurantRepo.findAllByDietRestrictions(dgs, dgs.size());
        
        if (restaurants.isEmpty()) throw new NoRestaurantMatchDietaryRequirements(
            clients.stream().map(Client::getName).collect(Collectors.toSet())
        );
        
        return restaurants;
    }
    
    public Reservation makeReservation(ReservationInfoDto info) throws RestaurantNotFoundWithThatName, ClientsNotRegisteredException, OversizedGroupException, NoTableAvailablesOnThisRestaurant {
        var clients = findClients(info);
        var restaurant = findRestaurantForReservation(info);
        
        int table = findTable(clients, restaurant, info);
        var range = Range.closed(info.startTime(), info.endTime());
        var date = info.date();
        
        var reservation = new Reservation(
            range,
            date,
            restaurant,
            table
        );
        
        var crd = clients.stream().map(c -> new ClientReservationData(
            range,
            date,
            reservation,
            c
        )).collect(Collectors.toSet());
        var saved = reservationRepo.save(reservation);
        clientReservationDataRepo.saveAll(crd);
        
        return saved;
    }
    
    private int findTable(List<Client> clients, Restaurant restaurant, ReservationInfoDto info) throws OversizedGroupException, NoTableAvailablesOnThisRestaurant {
        var tablesSizes = tablesThatCanHoldGroupofSize(clients.size());
        Integer table = null;
        
        for (int tableSize : tablesSizes) {
            var occupied = reservationRepo.findOccupiedTablesOfSize(
                restaurant.getId(),
                info.date(),
                tableSize,
                info.startTime(),
                info.endTime()
            );
            
            if (restaurant.tablesAvailableOfSize(tableSize, occupied) > 0) {
                table = tableSize;
                break;
            }
        }
        
        if (table == null) {
            throw new NoTableAvailablesOnThisRestaurant(restaurant.getName());
        }
        
        return table;
    }
    
    private Restaurant findRestaurantForReservation(ReservationInfoDto info) throws RestaurantNotFoundWithThatName {
        var opt = restaurantRepo.findByName(info.restaurantName());
        if (opt.isEmpty())
            throw new RestaurantNotFoundWithThatName(info.restaurantName());
        
        return opt.get();
    }
    
    public void removeReservation(UUID reservationId) {
        Optional<Reservation> opt = reservationRepo.findById(reservationId);
        if (opt.isEmpty()) return;
        var reservation = opt.get();
        
        List<ClientReservationData> allByReservation = clientReservationDataRepo.findAllByReservation(reservation);
        clientReservationDataRepo.deleteAll(allByReservation);
        reservationRepo.deleteById(reservationId);
    }
}
